package com.example.sau.info;


import com.example.sau.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private Long id;
    private String name;
    private double price;

 
    public ProductInfo(Optional<Product> product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
 


}
