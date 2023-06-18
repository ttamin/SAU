package com.example.sau.contoller;

import com.example.sau.global.GlobalData;
import com.example.sau.model.Blog;
import com.example.sau.model.Product;
import com.example.sau.service.BlogServiceImpl;
import com.example.sau.service.CategoryServiceImpl;
import com.example.sau.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;



@Controller
@RequestMapping("/home")
public class CustomerController {

    private final CategoryServiceImpl categoryService;
    private final ProductServiceImpl productServiceImpl;
    private final BlogServiceImpl blogService;

    public CustomerController(CategoryServiceImpl categoryService, ProductServiceImpl productServiceImpl, BlogServiceImpl blogService) {
        this.categoryService = categoryService;
        this.productServiceImpl = productServiceImpl;
        this.blogService = blogService;
    }


    @GetMapping("/shop")
    public String shop(Model model, @RequestParam(required = false) String errorMessage){
        try {
            model.addAttribute("categories", categoryService.getAllCategory());
            model.addAttribute("products", productServiceImpl.getAllProducts());
            model.addAttribute("cartCounter", GlobalData.cart.size());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error retrieving shop data");
            return "404customer";

        }
        if (errorMessage != null && errorMessage.equals("addToCartError")) {
            model.addAttribute("errorMessage", "Error adding item to cart.");
        }
        return "home/shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable long id){
        try {
            model.addAttribute("categories", categoryService.getAllCategory());
            model.addAttribute("cartCounter", GlobalData.cart.size());
            model.addAttribute("products", productServiceImpl.getAllProductsByCategoryId(id));
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error retrieving products by category");
            return "404customer";

        }

        return "home/shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable long id){
        try {
            Optional<Product> product = productServiceImpl.getProductById(id);
            if (product.isPresent()) {
                model.addAttribute("product", product.get());
                model.addAttribute("cartCounter", GlobalData.cart.size());
                return "home/viewproduct";
            } else {
                return "404customer";

            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error retrieving product");
            return "404customer";

        }
    }

    @GetMapping("/about-us")
    public String aboutUs(Model model){
        model.addAttribute("cartCounter", GlobalData.cart.size());

        return "home/about-us";
    }

    @GetMapping("/blogs")
    public String viewblogs(Model model){
        model.addAttribute("blogs", blogService.getAllBlogs());
        model.addAttribute("cartCounter", GlobalData.cart.size());
        return "home/blogPage";
    }

    @GetMapping("/blogs/{id}")
    public String blogDetails(Model model, @PathVariable long id){
        Blog blog = blogService.getBlogById(id).orElse(null);
        if (blog == null) {
            return "404customer";
        }
        model.addAttribute("blog", blog);
        model.addAttribute("cartCounter", GlobalData.cart.size());
        return "home/blogDetails";
    }

    @GetMapping("/info")
    public String infoPage(Model model){
        model.addAttribute("cartCounter", GlobalData.cart.size());
        return "home/info";
    }
}
