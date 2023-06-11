//package com.example.sau.config;

import com.example.sau.model.User;
import com.example.sau.repository.UserRepo;


import java.util.stream.Collectors;

//public class UserServiceConfig implements UserDetailsService {
//
//    private UserRepo userRepo;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByUsername(username);
//        if(user ==null){
//            throw new UsernameNotFoundException("Could not find username");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),
//                user.getPassword(),
//                user.getRoles()
//                .stream()
//                        .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
//    }
//}
