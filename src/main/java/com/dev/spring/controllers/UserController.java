package com.dev.spring.controllers;

import com.dev.spring.dto.UserResponseDto;
import com.dev.spring.model.User;
import com.dev.spring.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String getUser() {
        userService.add(new User("Bob", "bob@i.ua", ""));
        userService.add(new User("Alisa", "alisa@i.ua", ""));
        return "Data injected";
    }

    @GetMapping("/all")
    public List<UserResponseDto> getAll() {
        return userService.listUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.get(id);
    }
}
