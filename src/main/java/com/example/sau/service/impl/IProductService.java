package com.example.sau.service.impl;

import com.example.sau.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public List<Product> getAllProducts();
    public void addProduct(Product product);
    public void removeProductById(long id);
    public Optional<Product> getProductById(long id);
    public List<Product> getAllProductsByCategoryId(long id);


}
