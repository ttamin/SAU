package com.example.sau.contoller;

import com.example.sau.dto.UserDto;
import com.example.sau.model.User;
import com.example.sau.repository.RoleRepo;
import com.example.sau.service.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final  UserService userService;
    private final RoleRepo roleRepo;

    public AuthController(UserService userService, RoleRepo roleRepo) {
        this.userService = userService;
        this.roleRepo = roleRepo;
    }

    @GetMapping("/register")
    public String showRegistrarionForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleRepo.findAll());
        return "/user/register";
    }
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Такой адрес почты уже используется");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/user/register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

}


