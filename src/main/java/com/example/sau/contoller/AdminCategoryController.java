package com.example.sau.contoller;

import com.example.sau.model.Category;
import com.example.sau.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/main")
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
        return "redirect:/admin/categories/main";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCat(@PathVariable long id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories/main";
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

}
