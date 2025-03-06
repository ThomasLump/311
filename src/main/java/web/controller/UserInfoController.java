package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.UserCrudService;
import web.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    private UserCrudService userCrudService;

    public UserInfoController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    @GetMapping("/")
    public String getUserInfo(Model model, Principal principal) {
        model.addAttribute("userInfo", userCrudService.getUserDtoByName(principal.getName()));
        return "main";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/login?logout"; // Перенаправление на страницу логина с параметром logout
    }
}
