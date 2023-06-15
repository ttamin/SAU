package com.example.sau.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AdminController {
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

//    @GetMapping("/logout")
//    @GetMapping("/profile")??????????

}
