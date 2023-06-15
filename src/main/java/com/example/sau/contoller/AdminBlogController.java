package com.example.sau.contoller;

import com.example.sau.dto.BlogDto;
import com.example.sau.model.Blog;
import com.example.sau.service.BlogServiceImpl;
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
@RequestMapping("/admin/blogs")

public class AdminBlogController {
    public static String uplDir = System.getProperty("user.dir") +"/src/main/resources/static/blogImage";


    @Autowired
    BlogServiceImpl blogService;

    @GetMapping("/main")
    public String blogs(Model model){
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "blogs";
    }

    @GetMapping("/add")
    public String getBlogAdd(Model model){
        model.addAttribute("blogDto", new BlogDto());
        return "blogsAdd";
    }

    @PostMapping("/add")
    public String postProductAdd(@ModelAttribute("blogDto") BlogDto blogDto,
                                 @RequestParam("blogImage") MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException {
        Blog blog = new Blog();
        blog.setId(blogDto.getId());
        blog.setName(blogDto.getName());
        blog.setAnons(blogDto.getAnons());
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
        return "redirect:/admin/blogs/main";
    }


    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable long id){
        blogService.removeBlogById(id);
        return "redirect:/admin/blogs/main";

    }

    @GetMapping("/update/{id}")
    public String updateBlog(@PathVariable long id, Model model) {
        Blog blog = blogService.getBlogById(id).get();
        BlogDto blogDto = new BlogDto();
        blogDto.setId(blog.getId());
        blogDto.setName(blog.getName());
        blogDto.setAnons(blog.getAnons());


        blogDto.setFulltext(blog.getFulltext());
        blogDto.setImageName(blog.getImageName());

        model.addAttribute("blogDto", blogDto);

        return "blogsAdd";
    }
}
