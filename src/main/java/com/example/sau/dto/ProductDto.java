package com.example.sau.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Long categoryId;
    private double price;
    private String description;
    private String imageName;
    private int currentQuantity;
    private boolean isActive;
}
