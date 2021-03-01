package com.example.demo.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Error handleException(Exception e) {
        Error error = new Error();
        error.setCode(500);
        error.setMsg(e.getMessage());
        return error;
    }

    @Data
    private static class Error {
        private Integer code;
        private String msg;
    }
}