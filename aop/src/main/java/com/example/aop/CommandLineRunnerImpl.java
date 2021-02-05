package com.example.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(CommandLineRunnerImpl.class);
    private final AopService aopService;

    public CommandLineRunnerImpl(AopService aopService) {
        this.aopService = aopService;
    }

    @Override
    public void run(String... args) {
        aopService.testAop();
        log.info("commandLineRunnerImpl executed");
    }
}
