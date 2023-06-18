package com.example.sau.dto;

import com.example.sau.model.User;
import lombok.Data;

@Data
public class CustomerFormDto {
    private Long id;
    private User user;
    private String name;
    private String city;
    private String address;
    private String phone;
    private String email;
    private String paymentMethod;
}
