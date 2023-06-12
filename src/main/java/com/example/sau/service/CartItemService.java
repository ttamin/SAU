package com.example.sau.service;

import com.example.sau.model.CartItem;
import com.example.sau.repository.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    @Autowired
    CartItemRepo cartItemRepo;
    public CartItem getCartItemByProductId(Long id) {
        return cartItemRepo.findAllById(id);
    }
}
