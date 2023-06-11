package com.example.sau.service;

import com.example.sau.dto.UserDto;
import com.example.sau.exception.UserNotExistsException;
import com.example.sau.model.User;
import com.example.sau.repository.UserRepo;
import com.example.sau.service.impl.IUserService;
//import com.example.sau.util.IUserConverter;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepo userRepo;
//    private final IUserConverter userMapper;

    private static final Logger LOGGER = LogManager.getLogger(IUserService.class.getName());

    private static final String FIELD_IS_EMPTY = "Login or password shouldn't be empty";
    private static final String INVALID_FIELD = "Login or password shouldn't be less than 4 symbols";
    private static final String USER_IS_PRESENT = "User with login {} is already present";

//    private PasswordEncoder passwordEncoder;
//
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

//    @Override
//    @Transactional
//    public void save(UserDto userDto) {
//        User user = userMapper.fromUserDto(userDto);
//
//        userRepo.save(user);
//
//        LOGGER.info("New user : {}", user);
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
    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotExistsException("There is no user with such email"));
    }

    @Override
    @Transactional
    public boolean isInvalidUser(UserDto userDto) {
        return userDto.getUsername().length() < 4 || userDto.getPassword().length() < 4 || isUserPresent(userDto);
    }
    @Transactional
    public boolean isUserPresent(UserDto userDto) {
        String username = userDto.getUsername();
        return userRepo.findAll().stream().anyMatch(user -> username.equals(user.getUsername()));
    }
    @Override
    @Transactional
    public String invalidUser(UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            LOGGER.error(FIELD_IS_EMPTY);

            return FIELD_IS_EMPTY;
        }

        if (isUserPresent(userDto)) {
            LOGGER.error(USER_IS_PRESENT, username);

            return String.format(USER_IS_PRESENT.replace("{}", "%s"), username);
        }

        LOGGER.error(INVALID_FIELD);

        return INVALID_FIELD;
    }
}