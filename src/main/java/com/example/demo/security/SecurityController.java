package com.example.demo.security;

import com.example.demo.jpa.User;
import com.example.demo.jpa.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security")
public class SecurityController {
    private final UserService userService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findResources() {
        String answer = "You are now accessing secure resources";
        User user = userService.findUserById(1L);
        if (user != null) {
            answer = "Hello, " + user.getName() + ". " + answer;
        }
        return answer;
    }
}
