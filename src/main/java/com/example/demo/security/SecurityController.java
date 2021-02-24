package com.example.demo.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security")
public class SecurityController {
    @GetMapping
    public String findResources() {
        return "You are now accessing secure resources";
    }
}
