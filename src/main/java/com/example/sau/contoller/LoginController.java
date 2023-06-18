package com.example.sau.contoller;

import com.example.sau.dto.UserDto;
import com.example.sau.global.GlobalData;
import com.example.sau.model.User;
import com.example.sau.repository.UserRepo;
import com.example.sau.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String loginForm(Model model, HttpSession httpSession) {
        model.addAttribute("session", httpSession);
        return "user/login";
    }

    @GetMapping({"/admin"})
    public String adminHome(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        model.addAttribute("username", user.getUsername());
        return "admin/adminHome";
    }

    @GetMapping({"/home", ""})
    public String home(Model model) {

        model.addAttribute("cartCounter", GlobalData.cart.size());
        return "home/index";
    }

    @GetMapping("/change-pass")
    public String getChangePass() {
        return "user/change-pass";
    }

    @PostMapping("/update-pass")
    public String changePassword(UserDto userDto) {
        userService.changePassword(userDto);
        return "redirect:/home";
    }

}
