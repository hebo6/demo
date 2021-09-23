package com.example.demo.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConcurrentUtils {

    public static <T> T synchronize(Supplier<T> supplier, Lock lock) {
        if (lock.tryLock()) {
            try {
                return supplier.get();
            } finally {
                lock.unlock();
            }
        }
        return null;
    }
}
