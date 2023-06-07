package com.example.sau.contoller;

import com.example.sau.global.GlobalData;
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
    public String home(Model model) {
        model.addAttribute("cartCounter", GlobalData.cart.size());
        return "index";
    }


    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }
}
