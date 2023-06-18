package com.example.sau.service;

import com.example.sau.model.Category;
import com.example.sau.repository.CategoryRepo;
import com.example.sau.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }
    @Override
    public void addCategory(Category category){
        categoryRepo.save(category);
    }
    @Override
    public void removeCategoryById(long id){
        categoryRepo.deleteById(id);
    }
    @Override
    public Optional<Category> getCategoryById(long id){
        return categoryRepo.findById(id);
    }
}
