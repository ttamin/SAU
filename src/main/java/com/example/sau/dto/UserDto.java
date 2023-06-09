package com.example.sau.dto;

//import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
//    @Size(min=3, max = 10, message = "First name contains 3-10 characters" )
    private String firstname;
//    @Size(min=3, max = 10, message = "Last name contains 3-10 characters" )
    private String lastname;
    private String username;
//    @Size(min=5, max = 15, message = "Password contains 5-15 characters" )
    private String password;
}
