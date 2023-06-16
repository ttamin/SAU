package com.example.sau.contoller;

import com.example.sau.dto.UserDto;
import com.example.sau.model.User;
import com.example.sau.repository.RoleRepo;
import com.example.sau.service.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    RoleRepo roleRepo;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrarionForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleRepo.findAll());
        return "/users/register";
    }
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Este email ya esta registrado con una cuenta");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/users/register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }



}


