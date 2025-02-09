package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserCrudService;


@Controller
public class HomeController {
    private final UserCrudService userCrudService;

    public HomeController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }
    @ModelAttribute
    public void initModel(Model model) {
        model.addAttribute("userlist", userCrudService.getAllUsers());
        model.addAttribute("newuser", new User());
        model.addAttribute("updateuser", new User());
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/add")
    public String add(User user) {
        userCrudService.addUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("userId") long userId) {
        userCrudService.deleteUserById(userId);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "updateuser") User user) {
        userCrudService.updateUser(user);
        return "redirect:/";
    }
}
