package com.example.sau.config.service.userdetails;

import com.example.sau.constanta.UserRolesConstants;
import com.example.sau.model.Role;
import com.example.sau.model.User;
import com.example.sau.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.sau.constanta.UserRolesConstants.ADMIN;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Value("${spring.security.user.name}")
    private String adminUserName;
    @Value("${spring.security.user.password}")
    private String adminPassword;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByUsername(username);
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getRoles().getId() == 1L ? "ROLE_" + UserRolesConstants.USER:
//                "ROLE_"+UserRolesConstants.ADMIN));
//        return new CustomUserDetails(user.getId(), username, user.getPassword(), authorities);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals(adminUserName)) {
            List<GrantedAuthority> adminAuthorities = List.of(new SimpleGrantedAuthority("ROLE_" + ADMIN));
            return new CustomUserDetails(adminPassword, adminAuthorities, adminUserName, null);
        }
        else {
            User user = userRepo.findByUsername(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Role role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            }
            return new CustomUserDetails(user.getPassword(), authorities, username, user.getId());
        }
    }

}
