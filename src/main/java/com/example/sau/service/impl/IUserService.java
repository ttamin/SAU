package com.example.sau.service.impl;

import com.example.sau.dto.UserDto;
import com.example.sau.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User findByUsername(String username);
    User changePassword(UserDto userDto);
    User getUserByEmail(String email);
    boolean isInvalidUser(UserDto userDto);
    String invalidUser(UserDto userDto);

    public void save(UserDto userDto);

}
