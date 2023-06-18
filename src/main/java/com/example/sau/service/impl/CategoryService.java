package com.example.sau.service.impl;

import com.example.sau.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
     List<Category> getAllCategory();
     void addCategory(Category category);
     void removeCategoryById(long id);
     Optional<Category> getCategoryById(long id);
}
