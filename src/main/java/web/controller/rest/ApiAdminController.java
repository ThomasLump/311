package web.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.dto.NewUserDto;
import web.dto.UserDto;
import web.service.RoleService;
import web.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class ApiAdminController {
    private final UserService userService;
    private final RoleService roleService;

    public ApiAdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        Iterable<UserDto> users = userService.getAllUsers();
         return ResponseEntity.ok(users);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUser(@RequestBody NewUserDto userDto) {
        userService.addUserByDto(userDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto) {
        userService.updateUserByDto(userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
