package com.dev.spring.service;

import com.dev.spring.model.User;
import com.dev.spring.dto.UserResponseDto;
import java.util.List;

public interface UserService {
    User add(User user);

    List<UserResponseDto> listUsers();

    UserResponseDto get(Long id);
}
