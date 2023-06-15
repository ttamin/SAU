package com.example.sau.service;

import com.example.sau.dto.UserDto;
import com.example.sau.exception.UserAlreadyExistsException;
import com.example.sau.exception.UserNotExistsException;
import com.example.sau.model.User;
import com.example.sau.repository.UserRepo;
import com.example.sau.service.impl.UserService;
//import com.example.sau.util.IUserConverter;
import com.example.sau.utils.UserMapper;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
//    private final IUserConverter userMapper;

//    private static final Logger LOGGER = LogManager.getLogger(UserService.class.getName());
//
//    private static final String FIELD_IS_EMPTY = "Login or password shouldn't be empty";
//    private static final String INVALID_FIELD = "Login or password shouldn't be less than 4 symbols";
//    private static final String USER_IS_PRESENT = "User with login {} is already present";

    private PasswordEncoder passwordEncoder;

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
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User changePassword(UserDto userDto){
        User user = userRepo.findByUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return userRepo.save(user);
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotExistsException("There is no user with such email"));
    }
    @Override
    public UserDto create(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);

        User savedUser = userRepo.save(user);

        // Convert User JPA entity to UserDto
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        return savedUserDto;
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepo.findById(user.getId()).get();
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        User updatedUser = userRepo.save(existingUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        User user = optionalUser.get();
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }



//    @Override
//    @Transactional
//    public void save(UserDto userDto) {
//        User user = userMapper.fromUserDto(userDto);
//
//        userRepo.save(user);
//
//        LOGGER.info("New user : {}", user);
//    }



//    @Override
//    @Transactional
//    public boolean isInvalidUser(UserDto userDto) {
//        return userDto.getUsername().length() < 4 || userDto.getPassword().length() < 4 || isUserPresent(userDto);
//    }
//    @Transactional
//    public boolean isUserPresent(UserDto userDto) {
//        String username = userDto.getUsername();
//        return userRepo.findAll().stream().anyMatch(user -> username.equals(user.getUsername()));
//    }
//    @Override
//    @Transactional
//    public String invalidUser(UserDto userDto) {
//        String username = userDto.getUsername();
//        String password = userDto.getPassword();
//
//        if (username.isEmpty() || password.isEmpty()) {
//            LOGGER.error(FIELD_IS_EMPTY);
//
//            return FIELD_IS_EMPTY;
//        }
//
//        if (isUserPresent(userDto)) {
//            LOGGER.error(USER_IS_PRESENT, username);
//
//            return String.format(USER_IS_PRESENT.replace("{}", "%s"), username);
//        }
//
//        LOGGER.error(INVALID_FIELD);
//
//        return INVALID_FIELD;
//    }


}