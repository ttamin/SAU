package com.example.sau.service.impl;

import com.example.sau.dto.UserDto;
import com.example.sau.model.User;

import java.util.List;

public interface UserService {
//    User createUser(User user);

    User findByUsername(String username);
    User changePassword(UserDto userDto);
    User getUserByEmail(String email);
//    boolean isInvalidUser(UserDto userDto);
//    String invalidUser(UserDto userDto);


    UserDto create(UserDto user);
    UserDto updateUser(UserDto user);
    UserDto getUserById(Long userId);
    void deleteUser(Long userId);
    List<UserDto> getAllUsers();


//    public void save(UserDto userDto);

}
