package web.security;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final BeanFactory context;

    public SecurityConfig(BeanFactory context) {
        this.context = context;
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler getSuccessHandler() {
        return (request, response, authentication) -> {
            String redirectUrl = authentication.getAuthorities().stream().anyMatch(
                    authority -> "admin".equals(authority.getAuthority()))
                    ? "/admin/"
                    : "/user/";
            String token = context.getBean(JwtProvider.class).generateToken(authentication.getName());

            response.setHeader("Authorization", "Bearer " + token);
            response.setContentType("application/json");
            response.getWriter().write("{\"redirectUrl\": \"" + redirectUrl + "\"}");
            response.getWriter().flush();
        };
    }

    @Bean
    @Order(1)
    public SecurityFilterChain getRESTSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/api/**")
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer ->
                        configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(customizer ->
                        customizer
                                .anyRequest().authenticated()
                )
                .addFilterBefore(context.getBean(JwtAuthenticationFilter.class), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain getWebSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/admin/**", "/user/**", "/login", "/logout")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                            auth
                                    .requestMatchers("/admin/**").hasAuthority("admin")
                                    .requestMatchers("/user/**").hasAnyAuthority("admin", "user")
                                    .requestMatchers("/login").permitAll()
                                    .anyRequest().authenticated();
                        }
                )
                .formLogin(customizer -> customizer
                        .successHandler(getSuccessHandler())
                        .failureUrl("/login?error=true")
                        .loginPage("/login").permitAll()
                )
                .logout(config -> config
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }
}
