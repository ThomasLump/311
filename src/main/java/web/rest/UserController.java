package web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService service;

    @Autowired
    public UserController (UserService service) {
        this.service = service;

    }
    @GetMapping("/profile")
    @PreAuthorize("hasAnyAuthority('user')")
    public ResponseEntity<String> getProfile(Authentication auth) {
        return ResponseEntity.ok("");
    }


}
