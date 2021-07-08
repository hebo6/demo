package com.example.demo.aop;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {
    @AopAnnotation("I'm annotation")
    @GetMapping("aop")
    public String aop(String name) {
        Assert.notNull(name, "name = null");
        System.out.println("aop method executed");
        System.out.println("name = " + name);
        return "OK, name = " + name;
    }
}
