package com.example.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class AsyncService {
    private final Random random = new Random();

    @Async
    public void asyncPrintln() throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            long millis = 4000 + (random.nextLong() % 2000);
            log.info("times = " + i + ", duration = " + millis + "ms");
            Thread.sleep(millis);
        }
        long end = System.currentTimeMillis();
        log.info("done, duration = " + (end - start) + "ms");
    }
}
