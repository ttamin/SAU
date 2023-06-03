package com.example.sau.contoller;

import com.example.sau.model.Category;
import com.example.sau.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {


    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

}
