package com.example.sau.contoller;

import com.example.sau.model.User;
import com.example.sau.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public String listRegisteredUsers(Model model){
        List<User> roleUsers = userService.findAllUsers();
        model.addAttribute("users", roleUsers);
        return "/users/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return "redirect:/users";

    }
    @PostMapping("/{id}/promote")
    public String promoteUserToAdmin(@PathVariable("id") Long userId) {
        userService.updateUserRoleToAdmin(userId);
        return "redirect:/users";
    }
}
