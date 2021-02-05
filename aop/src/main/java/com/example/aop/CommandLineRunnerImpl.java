package com.example.aop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final AopService aopService;

    public CommandLineRunnerImpl(AopService aopService) {
        this.aopService = aopService;
    }

    @Override
    public void run(String... args) {
        aopService.testAop();
        System.out.println("commandLineRunnerImpl executed");
    }
}
