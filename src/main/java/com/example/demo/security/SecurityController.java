package com.example.demo.security;

import com.example.demo.jpa.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        String name = userService.whoAmI();
        if (name != null) {
            answer = "Hello, " + name + ". " + answer;
        }
        return answer;
    }

    @PostMapping
    public String addResources() {
        return "Success";
    }
}
