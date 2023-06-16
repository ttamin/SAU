package com.example.sau.service;

import com.example.sau.dto.UserDto;
import com.example.sau.exception.UserAlreadyExistsException;
import com.example.sau.exception.UserNotExistsException;
import com.example.sau.model.Role;
import com.example.sau.model.User;
import com.example.sau.repository.RoleRepo;
import com.example.sau.repository.UserRepo;
import com.example.sau.service.impl.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo,
                           PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userDto.getRoles());
        if (userDto.getRoles().isEmpty()) {
            Role role = roleRepo.findByName("ROLE_USER");
            if (role == null) {
                role = checkRoleExist("ROLE_USER");
            }
            user.setRoles(List.of(role));
        }
        userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }


    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(String roleName) {
        Role role = new Role();
        role.setName("roleName");
//        role.setName("ROLE_ADMIN");
        return roleRepo.save(role);
    }

    @Override
    public void updateUserRoleToAdmin(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            Role role = roleRepo.findByName("ROLE_ADMIN");
            if (role == null) {
                role = checkRoleExist("ROLE_ADMIN");
            }
            user.setRoles(List.of(role));
            userRepo.save(user);
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void removeUserRoleAdmin(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            Role role = roleRepo.findByName("ROLE_ADMIN");
            if (role != null) {
                user.getRoles().remove(role);
                userRepo.save(user);
            }
        }
    }
}
