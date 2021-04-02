package com.example.demo.aop;



import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;



@Component

public class AopCommandLineRunner implements CommandLineRunner {

    private final AopService aopService;



    public AopCommandLineRunner(AopService aopService) {

        this.aopService = aopService;

    }



    @Override

    public void run(String... args) throws Exception {

        aopService.aop();

    }

}


