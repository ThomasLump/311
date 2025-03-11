package web.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dto.UserDto;
import web.model.Role;
import web.service.RoleService;
import web.service.UserServiceCrud;

import java.security.Principal;
import java.util.Collections;



@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceCrud userServiceCrud;
    private final RoleService roleService;

    public AdminController(UserServiceCrud userServiceCrud, RoleService roleService) {
        this.userServiceCrud = userServiceCrud;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("userInfo", userServiceCrud.getUserDtoByName(principal.getName()));
        model.addAttribute("userlist", userServiceCrud.getAllUsers());
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
        userServiceCrud.addUserByDto(userDto);
        return "redirect:/admin/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("userId") long userId) {
        userServiceCrud.deleteUserById(userId);
        return "redirect:/admin/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute(name = "updateuser") UserDto userDto) {
        userServiceCrud.updateUserByDto(userDto);
        return "redirect:/admin/";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/login?logout"; // Перенаправление на страницу логина с параметром logout
    }
}
