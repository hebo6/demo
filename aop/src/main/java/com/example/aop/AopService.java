package com.example.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AopService {
    private static final Logger log = LoggerFactory.getLogger(AopService.class);
    @AopAnnotation("我是何波")
    public void testAop(){
        log.info("testAop executed");
    }
}
