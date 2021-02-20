package com.example.demo.shutdown;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LongProcessingController {
    @GetMapping("long-process")
    public String longProcess() throws InterruptedException {
        Thread.sleep(1000 * 10);
        return "Processing done, Duration = 10s";
    }
}
