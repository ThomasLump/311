package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dto.UserDto;
import web.model.Role;
import web.service.RoleService;
import web.service.UserCrudService;

import java.security.Principal;
import java.util.Collections;



@Controller
@RequestMapping("/admin")
public class UsersEditController {
    private UserCrudService userCrudService;
    private RoleService roleService;

    public UsersEditController(UserCrudService userCrudService, RoleService roleService) {
        this.userCrudService = userCrudService;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("userInfo", userCrudService.getUserDtoByName(principal.getName()));
        model.addAttribute("userlist", userCrudService.getAllUsers());
        model.addAttribute("newuser", new UserDto());
        model.addAttribute("updateuser", new UserDto());
        model.addAttribute("rolelist", roleService.getAll());
        model.addAttribute("newuserrole", new Role());
        return "main";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute(name = "newuser") UserDto userDto,
                      @RequestParam(name = "newuserrolename") String roleName) {
        UserDto updatedUserDto = new UserDto(
                userDto.id(),
                userDto.username(),
                userDto.phone_number(),
                Collections.singletonList(roleName) // заменяем список ролей на новый
        );
        userCrudService.addUserByDto(userDto);
        return "redirect:/admin/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("userId") long userId) {
        userCrudService.deleteUserById(userId);
        return "redirect:/admin/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "updateuser") UserDto userDto) {
        userCrudService.updateUserByDto(userDto);
        return "redirect:/admin/";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/login?logout"; // Перенаправление на страницу логина с параметром logout
    }
}
