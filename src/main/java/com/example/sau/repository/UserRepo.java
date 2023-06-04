package com.example.sau.repository;

import com.example.sau.dto.UserDto;
import com.example.sau.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User save(UserDto userDto);
    User update(UserDto userDto);
    User changePassword(UserDto userDto);
    boolean existsByUsername(String username);

}
