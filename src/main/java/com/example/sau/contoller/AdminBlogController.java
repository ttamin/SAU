package com.example.sau.contoller;

import com.example.sau.dto.BlogDto;
import com.example.sau.model.Blog;
import com.example.sau.service.BlogServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/admin/blogs")

public class AdminBlogController {
    public static String uplDir = System.getProperty("user.dir") +"/src/main/resources/static/blogImage";
    private final BlogServiceImpl blogService;

    public AdminBlogController(BlogServiceImpl blogService) {
        this.blogService = blogService;
    }

    @GetMapping("")
    public String blogs(Model model){
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "admin/blogs";
    }

    @GetMapping("/add")
    public String getBlogAdd(Model model){
        model.addAttribute("blogDto", new BlogDto());
        return "admin/blogsAdd";
    }

    @PostMapping("/add")
    public String postProductAdd(@ModelAttribute("blogDto") BlogDto blogDto,
                                 @RequestParam("blogImage") MultipartFile file,
                                 @RequestParam("imgName") String imgName) {
        Blog blog = new Blog();
        blog.setId(blogDto.getId());
        blog.setName(blogDto.getName());
        blog.setAnons(blogDto.getAnons());
        blog.setFulltext(blogDto.getFulltext());
        String imageUUID;
        try {
            if (!file.isEmpty()) {
                imageUUID = file.getOriginalFilename();
                Path fileNameAndPath = Paths.get(uplDir, imageUUID);
                Files.write(fileNameAndPath, file.getBytes());
            } else {
                imageUUID = imgName;
            }
            blog.setImageName(imageUUID);
            blogService.addBlog(blog);
        } catch (IOException e) {
            e.printStackTrace();

            return "404admin";
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable long id){
        try {
            blogService.removeBlogById(id);
            return "redirect:/admin/blogs";
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return "404admin";
        }

    }

    @GetMapping("/update/{id}")
    public String updateBlog(@PathVariable long id, Model model) {
        try {
            Blog blog = blogService.getBlogById(id).orElse(null);
            if (blog == null) {
                throw new Exception("Blog not found with id: " + id);
            }
            BlogDto blogDto = new BlogDto();
            blogDto.setId(blog.getId());
            blogDto.setName(blog.getName());
            blogDto.setAnons(blog.getAnons());
            blogDto.setFulltext(blog.getFulltext());
            blogDto.setImageName(blog.getImageName());

            model.addAttribute("blogDto", blogDto);
            return "admin/blogsAdd";
        } catch (Exception e) {
            e.printStackTrace();
            return "404admin";
        }
    }
}
