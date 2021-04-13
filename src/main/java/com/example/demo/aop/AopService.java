package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AopService {
    @AopAnnotation("i'm annotation")
    public void aop() {
        log.info("Aop method executed");
    }
}
