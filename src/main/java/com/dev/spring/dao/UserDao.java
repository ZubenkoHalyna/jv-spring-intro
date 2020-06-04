package com.dev.spring.dao;

import com.dev.spring.dto.UserResponseDto;
import com.dev.spring.model.User;
import java.util.List;

public interface UserDao {
    User add(User user);

    List<UserResponseDto> getAll();

    UserResponseDto get(Long id);
}
