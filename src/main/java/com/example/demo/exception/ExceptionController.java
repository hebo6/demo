package com.example.demo.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exception")
public class ExceptionController {
    @GetMapping
    public String returnException() {
        throw new RuntimeException("It's wrong");
    }
}
