package com.example.sau.contoller;

import com.example.sau.global.GlobalData;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginForm(Model model, HttpSession httpSession) {
        model.addAttribute("session", httpSession);
        return "login";
    }

    @GetMapping({"/admin"})
    public String adminHome(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        //model.addAttribute("session", username);
        return "admin/adminHome";
    }

    @GetMapping({"/home", ""})
    public String home(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("cartCounter", GlobalData.cart.size());
        return "home/index";
    }


}
