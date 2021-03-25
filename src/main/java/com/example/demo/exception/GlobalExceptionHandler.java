package com.example.demo.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Error handleException(Exception e) {
        log.error("捕获到异常", e);
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
