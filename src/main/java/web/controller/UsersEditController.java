package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.RoleDao;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserCrudService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class UsersEditController {
    private final UserCrudService userCrudService;
    private RoleService roleService;

    public UsersEditController(UserCrudService userCrudService, RoleService roleService) {
        this.userCrudService = userCrudService;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("userInfo", userCrudService.getUserByName(principal.getName()));
        model.addAttribute("userlist", userCrudService.getAllUsers());
        model.addAttribute("newuser", new User());
        model.addAttribute("updateuser", new User());
        model.addAttribute("rolelist", roleService.getAll());
        model.addAttribute("newuserrole", new Role());
        return "main";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute(name = "newuser") User user,
                      @RequestParam(name = "newuserrolename") String roleName) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRole(roleName).orElseThrow());
        user.setRole(roles);
        userCrudService.addUser(user);
        return "redirect:/admin/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("userId") long userId) {
        userCrudService.deleteUserById(userId);
        return "redirect:/admin/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "updateuser") User user) {
        userCrudService.updateUser(user);
        return "redirect:/admin/";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/login?logout"; // Перенаправление на страницу логина с параметром logout
    }
}
