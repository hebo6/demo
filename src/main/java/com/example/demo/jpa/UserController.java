package com.example.demo.jpa;

import com.example.demo.jpa.entity.User;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("user/{id}")
    public User findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("users")
    public List<User> saveUsers(@RequestBody List<User> users) {
        return userService.saveUsers(users);
    }
}
