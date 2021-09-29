package com.example.demo.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConcurrentUtils {
    private static final Map<Object, Lock> obj2Lock = new ConcurrentHashMap<>();

    /**
     * @param supplier 要执行的函数
     */
    public static <T> T synchronize(Supplier<T> supplier, Object obj) {
        Lock lock = lock(obj);

        try {
            return supplier.get();
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param processor 要执行的函数
     */
    public static void synchronize(Processor processor, Object obj) {
        Lock lock = lock(obj);

        try {
            processor.run();
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param obj 与锁对应的对象
     * @throws RuntimeException 如果获取锁失败
     */
    private static Lock lock(Object obj) {
        Lock lock = obj2Lock.get(obj);
        if (lock == null) {
            synchronized (obj2Lock) {
                if (obj2Lock.get(obj) == null) {
                    obj2Lock.put(obj, new ReentrantLock());
                }
            }
            lock = obj2Lock.get(obj);
        }

        if (!lock.tryLock()) {
            throw new RuntimeException("任务已在执行中");
        }
        return lock;
    }
}
