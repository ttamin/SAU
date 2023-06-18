package com.example.sau.service;

import com.example.sau.model.Product;
import com.example.sau.repository.ProductRepo;
import com.example.sau.service.impl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    @Override

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
    @Override

    public void addProduct(Product product){
        productRepo.save(product);
    }
    @Override

    public void removeProductById(long id){
        productRepo.deleteById(id);
    }
    @Override

    public Optional<Product> getProductById(long id){
        return productRepo.findById(id);
    }
    @Override

    public List<Product> getAllProductsByCategoryId(long id){
        return productRepo.findAllByCategory_Id(id);
    }
}
