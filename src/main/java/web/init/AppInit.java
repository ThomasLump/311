package web.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserCrudService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.StreamSupport;

@Component
public class AppInit implements CommandLineRunner {
    private UserCrudService userCrudService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public AppInit(UserCrudService userCrudService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userCrudService = userCrudService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) throws Exception {
        addAdmin();
    }

    protected void addAdmin() {
        if(StreamSupport.stream(userCrudService.getAllUsers().spliterator(),false)
                .noneMatch(user-> "admin".equals(user.getUsername()))) {
            User initUser = new User();
            initUser.setUsername("admin");
            initUser.setPassword(passwordEncoder.encode("0000"));
            Role initRole = roleService.getRole("admin").orElseGet(() -> {
                Role addedRole = new Role();
                addedRole.setName("admin");
                roleService.addNew(addedRole);
                return addedRole;
            });
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRole(initRole.getName()).orElseThrow());
            initUser.setRole(roles);
            userCrudService.updateUser(initUser);
        }
    }


}
