package com.example.sau.service.impl;

import com.example.sau.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
     List<Product> getAllProducts();
     void addProduct(Product product);
     void removeProductById(long id);
     Optional<Product> getProductById(long id);
     List<Product> getAllProductsByCategoryId(long id);


}
