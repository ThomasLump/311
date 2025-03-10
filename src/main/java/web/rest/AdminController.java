package web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.dto.UserDto;
import web.service.RoleService;
import web.service.UserServiceCrud;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    UserServiceCrud userService;
    RoleService roleService;

    @Autowired
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

    //test
    //test2 - 2
}
