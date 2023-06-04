package com.example.sau.service.impl;

import com.example.sau.model.User;

import java.util.List;

public interface IUserService {
    User createUser(User user);
    List<User> getAllUsers();
    public User findByUsername(String username);
}
