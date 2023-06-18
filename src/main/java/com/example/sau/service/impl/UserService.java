package com.example.sau.service.impl;

import com.example.sau.dto.UserDto;
import com.example.sau.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);


    List<User> findAllUsers();

    void updateUserRoleToAdmin(Long userId);

    void deleteUserById(Long id);

    public void removeUserRoleAdmin(Long userId);
    public User changePassword(UserDto userDto);

}
