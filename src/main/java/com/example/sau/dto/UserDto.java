package com.example.sau.dto;

//import jakarta.validation.constraints.Size;
import com.example.sau.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    private String username;
    @Email
    @NotEmpty(message = "Почта не может быть пустым")
    private String email;
    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;
    private List<Role> roles = new ArrayList<>();

}
