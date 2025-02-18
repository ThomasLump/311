package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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
        System.out.println("getSuccessHandler");
        return (request, response, authentication) -> {
            System.out.println("successHandler invoked");
            response.sendRedirect(
                    authentication.getAuthorities().stream().anyMatch(authority -> "admin".equals(authority.getAuthority()))
                            ? "/admin/"
                            : "/user/"
            );
        };
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("getSecurityChain");
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> {
                auth
                                .requestMatchers("/admin/**").hasAuthority("admin")
                                .requestMatchers("/user/**").hasAnyAuthority("admin","user")
                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated();
                    }
            )
            .formLogin(customizer -> customizer
                .successHandler(getSuccessHandler())
                .failureUrl("/login?error=true")
            )
                .logout( config -> config
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }
}
