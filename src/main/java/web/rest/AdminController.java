package web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.dto.UserDto;
import web.service.RoleService;
import web.service.UserCrudService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    UserCrudService userCrudService;
    RoleService roleService;


    public AdminController(RoleService roleService, UserCrudService userCrudService) {
        this.roleService = roleService;
        this.userCrudService = userCrudService;
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        Iterable<UserDto> users = userCrudService.getAllUsers();
         return ResponseEntity.ok(users);
    }



}
