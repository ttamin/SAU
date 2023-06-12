//package com.example.sau.form;
//
//
//import com.example.sau.model.Product;
//import lombok.Data;
//import org.springframework.web.multipart.MultipartFile;
//
//@Data
//public class ProductForm {
//    private Long id;
//    private String name;
//    private double price;
//
//    private boolean newProduct = false;
//
//    // Upload file.
//    private MultipartFile fileData;
//
//    public ProductForm() {
//        this.newProduct= true;
//    }
//
//    public ProductForm(Product product) {
//        this.id = product.getId();
//        this.name = product.getName();
//        this.price = product.getPrice();
//    }
//
//
//}
