package web.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.dto.NewUserDto;
import web.dto.UserDto;
import web.service.RoleService;
import web.service.UserServiceCrud;

@RestController
@RequestMapping("/api/admin")
public class ApiAdminController {
    private final UserServiceCrud userService;
    private final RoleService roleService;

    public ApiAdminController(RoleService roleService, UserServiceCrud userServiceCrud) {
        this.roleService = roleService;
        this.userService = userServiceCrud;
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        System.out.println("🚀REST USERS");
        Iterable<UserDto> users = userService.getAllUsers();
        System.out.println(users);
         return ResponseEntity.ok(users);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUser(@RequestBody NewUserDto userDto) {
        System.out.println("🚀REST ADD");
        userService.addUserByDto(userDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto) {
        userService.updateUserByDto(userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        System.out.println("🚀REST DELETE");
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
