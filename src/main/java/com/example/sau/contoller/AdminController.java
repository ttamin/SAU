package com.example.sau.contoller;

import com.example.sau.dto.BlogDto;
import com.example.sau.dto.ProductDto;
import com.example.sau.model.Blog;
import com.example.sau.model.Category;
import com.example.sau.model.Product;
import com.example.sau.service.BlogService;
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
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") +"/src/main/resources/static/productImages";
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;
    public static String uplDir = System.getProperty("user.dir") +"/src/main/resources/static/blogImage";


    @Autowired
    BlogService blogService;
    @GetMapping("/categories")
    public String getCategories(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCat(@PathVariable long id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/update/{id}")
    public String updateCat(@PathVariable long id, Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else
            return "404";
    }


    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products",productService.getAllProducts());
        return "products";
    }

    @GetMapping("/products/add")
    public String getProductAdd(Model model){
        model.addAttribute("productDto",new ProductDto());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/products/add")
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

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/update/{id}")
    public String updateProduct(@PathVariable long id, Model model){
        Product product = productService.getProductById(id).get();
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

        return "productsAdd";

    }


    @GetMapping("/blogs")
    public String blogs(Model model){
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "blogs";
    }

    @GetMapping("/blogs/add")
    public String getBlogAdd(Model model){
        model.addAttribute("blogDto", new BlogDto());
        return "blogsAdd";
    }

    @PostMapping("/blogs/add")
    public String postProductAdd(@ModelAttribute("blogDto") BlogDto blogDto,
                                 @RequestParam("blogImage") MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException {
        Blog blog = new Blog();
        blog.setId(blogDto.getId());
        blog.setName(blogDto.getName());
        blog.setFulltext(blogDto.getFulltext());
        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uplDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID=imgName;
        }
        blog.setImageName(imageUUID);
        blogService.addBlog(blog);
        return "redirect:/admin/blogs";
    }


    @GetMapping("/blogs/delete/{id}")
    public String deleteBlog(@PathVariable long id){
        blogService.removeBlogById(id);
        return "redirect:/admin/blogs";

    }

    @GetMapping("/blogs/update/{id}")
    public String updateBlog(@PathVariable long id, Model model) {
        Blog blog = blogService.getBlogById(id).get();
        BlogDto blogDto = new BlogDto();
        blogDto.setId(blog.getId());
        blogDto.setName(blog.getName());
        blogDto.setFulltext(blog.getFulltext());
        blogDto.setImageName(blog.getImageName());

        model.addAttribute("blogDto", blogDto);

        return "blogsAdd";
    }

}
