package com.example.sau.service;

import com.example.sau.dto.UserDto;
import com.example.sau.exception.UserAlreadyExistsException;
import com.example.sau.exception.UserNotExistsException;
import com.example.sau.model.User;
import com.example.sau.repository.UserRepo;
import com.example.sau.service.impl.IUserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepo userRepo;
//    private PasswordEncoder passwordEncoder;

//    @Override
//    public User createUser(User user) {
//        if (userRepo.existsByUsername(user.getUsername())) {
//            throw new UserAlreadyExistsException("User with that phone is already registered");
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        return userRepo.save(user);
//    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User changePassword(UserDto userDto){
        User user = userRepo.findByUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return userRepo.save(user);
    }
}