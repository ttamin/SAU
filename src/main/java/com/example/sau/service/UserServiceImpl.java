package com.example.sau.service;

import com.example.sau.dto.UserDto;
import com.example.sau.model.Role;
import com.example.sau.model.User;
import com.example.sau.repository.RoleRepo;
import com.example.sau.repository.UserRepo;
import com.example.sau.service.impl.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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


    private Role checkRoleExist(String roleName) {
        Role role = new Role();
        role.setName("roleName");
//        role.setName("ROLE_ADMIN");
        return roleRepo.save(role);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User changePassword(UserDto userDto){
        User user = userRepo.findByEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepo.save(user);
    }
    @Override
    public void removeUserRoleUser(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            Role role = roleRepo.findByName("ROLE_USER");
            if (role != null) {
                user.getRoles().remove(role);
                userRepo.save(user);
            }
        }
    }

//    @Override
//    @Transactional
//    public void changeUserRole(Long id, Long roleId) {
//        User user = userRepo.findById(id).orElse(null);
//        Role role = roleRepo.findById(roleId).orElse(null);
//
//        if (user != null && role != null) {
//            user.setRoles(Collections.singletonList(role));
//            userRepo.save(user);
//        }
//    }


}
