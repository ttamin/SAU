package com.example.sau.contoller;

import com.example.sau.dto.ProductDto;
import com.example.sau.model.Product;
import com.example.sau.service.CategoryService;
import com.example.sau.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class ProductController {
    public static String uploadDir = System.getProperty("user.dir") +"/src/main/resources/static/productImages";
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products",productService.getAllProducts());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String getProductAdd(Model model){
        model.addAttribute("productDto",new ProductDto());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String postProductAdd(@ModelAttribute("productDto") ProductDto productDto,
                                 @RequestParam("productImage")MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException{
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()).get());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
        imageUUID=imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

}
