package web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.dto.UserDto;
import web.service.RoleService;
import web.service.UserServiceCrud;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    UserServiceCrud userServiceCrud;
    RoleService roleService;

    @Autowired
    public AdminController(RoleService roleService, UserServiceCrud userServiceCrud) {
        this.roleService = roleService;
        this.userServiceCrud = userServiceCrud;
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        Iterable<UserDto> users = userServiceCrud.getAllUsers();
         return ResponseEntity.ok(users);
    }



}
