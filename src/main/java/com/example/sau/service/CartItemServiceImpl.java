package com.example.sau.service;

import com.example.sau.model.CartItem;
import com.example.sau.repository.CartItemRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl {
    private final CartItemRepo cartItemRepo;

    public CartItemServiceImpl(CartItemRepo cartItemRepo) {
        this.cartItemRepo = cartItemRepo;
    }

    public Optional<CartItem> getCartItemByProductId(Long id) {
        return cartItemRepo.findById(id);
    }
}
