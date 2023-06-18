package com.example.sau.contoller;

import com.example.sau.model.Category;
import com.example.sau.service.CategoryServiceImpl;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final CategoryServiceImpl categoryService;

    public AdminCategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String getCategories(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "admin/categories";
    }

    @GetMapping("/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());
        return "admin/categoriesAdd";
    }

    @PostMapping("/add")
    public String postCatAdd(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCat(@PathVariable long id){
        try {
            categoryService.removeCategoryById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "404admin";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/update/{id}")
    public String updateCat(@PathVariable long id, Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            return "admin/categoriesAdd";
        } else
            return "404admin";
    }

}
