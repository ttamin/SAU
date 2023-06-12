package com.example.sau.global;

import com.example.sau.model.CartItem;
import com.example.sau.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<CartItem> cart;
    static {
        cart = new ArrayList<>();
    }


}
