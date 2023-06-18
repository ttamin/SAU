package com.example.sau.contoller;

import com.example.sau.dto.UserDto;
import com.example.sau.global.GlobalData;
import com.example.sau.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

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
    @GetMapping("/forgot-pass")
    public String forgotPass() {
        return "user/forgot-pass";
    }

}
