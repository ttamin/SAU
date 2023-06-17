package com.example.sau.contoller;

import com.example.sau.dto.ProductDto;
import com.example.sau.model.Product;
import com.example.sau.service.CategoryServiceImpl;
import com.example.sau.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    public static String uploadDir = System.getProperty("user.dir") +"/src/main/resources/static/productImages";
    @Autowired
    ProductServiceImpl productServiceImpl;

    @Autowired
    CategoryServiceImpl categoryService;
    @GetMapping("")
    public String products(Model model){
        model.addAttribute("products", productServiceImpl.getAllProducts());
        return "admin/products";
    }

    @GetMapping("/add")
    public String getProductAdd(Model model){
        model.addAttribute("productDto",new ProductDto());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "admin/productsAdd";
    }

    @PostMapping("/add")
    public String postProductAdd(@ModelAttribute("productDto") ProductDto productDto,
                                 @RequestParam("productImage") MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()).get());
        product.setCurrentQuantity(productDto.getCurrentQuantity());
        product.setActive(productDto.isActive());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        String imageUUID;
        try {
            if (!file.isEmpty()) {
                imageUUID = file.getOriginalFilename();
                Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
                Files.write(fileNameAndPath, file.getBytes());
            } else {
                imageUUID = imgName;
            }
            product.setImageName(imageUUID);
            productServiceImpl.addProduct(product);
        } catch (IOException e) {
            e.printStackTrace();
            return "error-page/product-error";
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productServiceImpl.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable long id, Model model){
        Product product = productServiceImpl.getProductById(id).get();
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setCurrentQuantity(product.getCurrentQuantity());
        productDto.setActive(product.isActive());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImageName(product.getImageName());

        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDto", productDto);

        return "admin/productsAdd";

    }
}
