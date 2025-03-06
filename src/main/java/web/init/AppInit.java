package web.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserCrudService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.StreamSupport;

@Component
public class AppInit implements CommandLineRunner {
    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    public AppInit(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        addAdmin();
    }

    protected void addAdmin() {
        if(userDao.getAllUsers().stream()
                .noneMatch(user-> "admin".equals(user.getUsername()))) {
            User initUser = new User();
            initUser.setUsername("admin");
            initUser.setPassword(passwordEncoder.encode("0000"));
            Role initRole = roleDao.findRoleByName("admin").orElseGet(() -> {
                Role addedRole = new Role();
                addedRole.setName("admin");
                roleDao.postRole(addedRole);
                return addedRole;
            });
            Set<Role> roles = new HashSet<>();
            roles.add(roleDao.findRoleByName(initRole.getName()).orElseThrow());
            initUser.setRole(roles);
            userDao.updateUser(initUser);
        }
    }


}
