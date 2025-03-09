package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.UserServiceCrud;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserServiceCrud userServiceCrud;

    public UserController(UserServiceCrud userServiceCrud) {
        this.userServiceCrud = userServiceCrud;
    }

    @GetMapping("/")
    public String getUserInfo(Model model, Principal principal) {
        model.addAttribute("userInfo", userServiceCrud.getUserDtoByName(principal.getName()));
        return "main";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/login?logout"; // Перенаправление на страницу логина с параметром logout
    }
}
