package com.example.sau.config;

import com.example.sau.model.User;
import com.example.sau.repository.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    private static final Logger LOGGER = LogManager.getLogger(CustomUserDetailService.class.getName());


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepo.findByUsername(username);

        LOGGER.info("User : {}", user);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetail(user);
    }
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> user = userRepo.findByEmail(email);
//        user.orElseThrow(()->new UsernameNotFoundException("There are no User with such email"));
//        return user.map(CustomUserDetail::new).get();
//    }
}
