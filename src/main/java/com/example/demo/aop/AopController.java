package com.example.demo.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {
    private final AopService aopService;

    public AopController(AopService aopService) {
        this.aopService = aopService;
    }

    @GetMapping("aop")
    public String aop() {
        aopService.aop();
        return "Done, Please see console log";
    }
}
