package com.dev.spring.service;

import com.dev.spring.dao.UserDao;
import com.dev.spring.dto.UserResponseDto;
import com.dev.spring.model.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public UserResponseDto get(Long id) {
        return userDao.get(id);
    }

    @Override
    public List<UserResponseDto> listUsers() {
        return userDao.getAll();
    }
}
