package com.example.sau.contoller;

import com.example.sau.service.CategoryService;
import com.example.sau.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProducts());
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable long id){
        model.addAttribute("product", productService.getProductById(id).get());
        return "viewproduct";
    }

}
