//package com.example.sau.contoller;
//
//import com.example.sau.dto.UserDto;
//import com.example.sau.global.GlobalData;
//import com.example.sau.model.Role;
//import com.example.sau.model.User;
//import com.example.sau.repository.RoleRepo;
//import com.example.sau.repository.UserRepo;
//import com.example.sau.service.UserService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class LoginController {
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Autowired
//    UserRepo userRepo;
//    @Autowired
//    RoleRepo roleRepo;
//    @Autowired
//    UserService userService;
//
//    @GetMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("title", "Login Page");
//        GlobalData.cart.clear();
//        return "login";
//    }
//    @GetMapping("/register")
//    public String registerGet(Model model) {
//        System.out.println("sdf");
//        model.addAttribute("title", "Register");
//        model.addAttribute("userDto", new UserDto());
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException{
//        String password = user.getPassword();
//        user.setPassword(bCryptPasswordEncoder.encode(password));
//        List<Role> roles = new ArrayList<>();
//        roles.add(roleRepo.findById(2L).get());
//        user.setRoles(roles);
//        request.login(user.getEmail(), password);
//        return "redirect:/";
//    }
//
////    @PostMapping("/register")
////    public String saveUser(Model model, HttpServletRequest request, @ModelAttribute UserDto userDto) {
////        if (userService.isInvalidUser(userDto)) {
////            String errors = userService.invalidUser(userDto);
////
////            model.addAttribute("validationErrors", errors);
////
////            return "register";
////        }
////
////        return eventsWithCheckbox(request, userDto);
////    }
//
//    private String eventsWithCheckbox(HttpServletRequest request, @ModelAttribute UserDto userDto) {
//        if (request.getParameter("isUserCheck") != null) {
//
//            userService.save(userDto);
//
//            return "redirect:/login";
//        } else {
//            return "checkboxError";
//        }
//    }
//}
