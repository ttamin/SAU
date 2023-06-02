package com.example.sau.service;

import com.example.sau.model.Product;
import com.example.sau.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
}
