package web.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.dto.UserDto;
import web.service.RoleService;
import web.service.UserServiceCrud;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserServiceCrud userService;
    private final RoleService roleService;

    public AdminController(RoleService roleService, UserServiceCrud userServiceCrud) {
        this.roleService = roleService;
        this.userService = userServiceCrud;
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        Iterable<UserDto> users = userService.getAllUsers();
         return ResponseEntity.ok(users);
    }

    @GetMapping("/add")
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        userService.addUserByDto(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto) {
        userService.updateUserByDto(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestBody Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
