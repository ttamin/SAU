package com.example.sau.service.impl;

import com.example.sau.exception.NotEnoughProductsInStockException;
import com.example.sau.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface IShoppingCartService {
    void addProduct(Product product);
    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();
    void checkout() throws NotEnoughProductsInStockException;
    BigDecimal getTotal();

}
