//package com.example.sau.util;
//
//import com.example.sau.dto.UserDto;
//import com.example.sau.model.User;
//import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//
//
//@Component
//@AllArgsConstructor
//public class UserConverter implements IUserConverter {
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public User fromUserDto(UserDto userDto) {
//        User user = new User();
//
//        user.setUsername(userDto.getUsername());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//        return user;
//    }
//
//
//}