package com.example.shutdown.graceful;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LongProcessingController {
    @GetMapping("greeting")
    public String greeting() throws InterruptedException {
        Thread.sleep(1000 * 10);
        return "Hello Demo";
    }
}
