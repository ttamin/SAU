package com.example.sau.contoller;

import com.example.sau.model.User;
import com.example.sau.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/admin/users")
public class AdminUserController {
    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String listRegisteredUsers(Model model){
        List<User> roleUsers = userService.findAllUsers();
        model.addAttribute("users", roleUsers);
        return "/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.removeUserRoleUser(id);
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }

}
