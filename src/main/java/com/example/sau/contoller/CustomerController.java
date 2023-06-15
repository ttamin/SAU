package com.example.sau.contoller;

import com.example.sau.global.GlobalData;
import com.example.sau.service.BlogServiceImpl;
import com.example.sau.service.CategoryServiceImpl;
import com.example.sau.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class CustomerController {
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    ProductServiceImpl productServiceImpl;
    @Autowired
    BlogServiceImpl blogService;

    @GetMapping({ ""})
    public String home(Model model) {
        model.addAttribute("cartCounter", GlobalData.cart.size());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productServiceImpl.getAllProducts());
        model.addAttribute("cartCounter", GlobalData.cart.size());

        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable long id){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("cartCounter", GlobalData.cart.size());
        model.addAttribute("products", productServiceImpl.getAllProductsByCategoryId(id));
        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable long id){
        model.addAttribute("product", productServiceImpl.getProductById(id).get());
        model.addAttribute("cartCounter", GlobalData.cart.size());
        return "viewproduct";
    }

    @GetMapping("/about-us")
    public String aboutUs(Model model){
        model.addAttribute("cartCounter", GlobalData.cart.size());

        return "about-us";
    }

    @GetMapping("/blogs")
    public String viewblogs(Model model){
        model.addAttribute("blogs", blogService.getAllBlogs());
        model.addAttribute("cartCounter", GlobalData.cart.size());

        return "blogPage";
    }

    @GetMapping("/blogs/{id}")
    public String blogDetails(Model model, @PathVariable long id){
        model.addAttribute("blog", blogService.getBlogById(id).get());
        model.addAttribute("cartCounter", GlobalData.cart.size());

        return "blogDetails";
    }

    @GetMapping("/info")
    public String infoPage(Model model){
        model.addAttribute("cartCounter", GlobalData.cart.size());
        return "info";
    }
}
