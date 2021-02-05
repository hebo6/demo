package com.example.aop;

import org.springframework.stereotype.Service;

@Service
public class AopService {
    @AopAnnotation("我是何波")
    public void testAop(){
        System.out.println("testAop executed");
    }
}
