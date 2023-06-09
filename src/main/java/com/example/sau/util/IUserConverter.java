package com.example.sau.util;

import com.example.sau.dto.UserDto;
import com.example.sau.model.User;

public interface IUserConverter {
    User fromUserDto(UserDto userDto);

}
