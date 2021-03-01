package com.example.demo.async;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("async")
public class AsyncController {
    private final AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping
    public String asyncPrintln() throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            asyncService.asyncPrintln();
        }
        return "Called successful, seeing the console for result";
    }
}
