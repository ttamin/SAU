package com.example.sau.service;

import com.example.sau.model.Category;
import com.example.sau.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }

    public void addCategory(Category category){
        categoryRepo.save(category);
    }
    public void removeCategoryById(long id){
        categoryRepo.deleteById(id);
    }
}
