package com.example.sau.contoller;

import com.example.sau.dto.UserDto;
import com.example.sau.service.CategoryService;
import com.example.sau.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }
//
//    @GetMapping({"/", "/home"})
//    public String home(Model model) {
//        model.addAttribute("title", "Home Page");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "redirect:/login";
//        }
//        return "home";
//    }
//
//
//    @GetMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("title", "Login Page");
//        return "login";
//    }
//    @GetMapping("/register")
//    public String register(Model model) {
//        model.addAttribute("title", "Register");
//        model.addAttribute("userDto", new UserDto());
//        return "register";
//    }

}
