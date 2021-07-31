package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class RetryUtils {
    private RetryUtils() {
    }

    public static <T> T retryable(Supplier<T> supplier) {
        return retryable(supplier, 3);
    }

    public static <T> T retryable(Supplier<T> supplier, int maxTimes) {
        int times = 0;
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                times++;
                log.warn("retryable失败, 已失败次数 times = {}", times, e);
                if (times >= maxTimes) {
                    throw new RuntimeException("调用retryable失败已达最大次数, msg = " + e.getMessage());
                }
            }
        }
    }
}
