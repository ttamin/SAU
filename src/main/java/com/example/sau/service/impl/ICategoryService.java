package com.example.sau.service.impl;

import com.example.sau.model.Category;
import com.example.sau.model.Product;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    public List<Category> getAllCategory();
    public void addCategory(Category category);
    public void removeCategoryById(long id);
    public Optional<Category> getCategoryById(long id);
}
