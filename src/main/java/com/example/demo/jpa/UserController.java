package com.example.demo.jpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public List<User> findUsers() {
        return userService.findUsers();
    }

    @PostMapping("users")
    public List<User> insertUsers(@RequestBody List<User> users) {
        return userService.insertUsers(users);
    }
}
