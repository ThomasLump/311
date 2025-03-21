package web.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.dto.UserDto;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
    private final UserService userService;
    private final RoleService roleService;

    public ApiUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyAuthority('user')")
    public ResponseEntity<UserDto> getProfile(Authentication auth) {
        return ResponseEntity.ok(userService.getUserDtoByName(auth.getName()));
    }

    @GetMapping("/header")
    public ResponseEntity<UserDto> getHeader(Authentication auth) {
        return ResponseEntity.ok(userService.getUserDtoByName(auth.getName()));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<String>> getRoles() {
        return ResponseEntity.ok(roleService.getAll().stream()
                .map(role -> role.getName())
                .toList());
    }
}
