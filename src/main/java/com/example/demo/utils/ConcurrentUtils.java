package com.example.demo.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConcurrentUtils {
    private static final Map<Object, Lock> obj2Lock = new ConcurrentHashMap<>();

    public static void synchronize(Processor processor, Object obj) {
        synchronize(processor, obj, FallBackMethod.THROW_EXCEPTION);
    }

    /**
     * @param processor 要执行的函数
     */
    public static void synchronize(Processor processor, Object obj, FallBackMethod method) {
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
            if (method == FallBackMethod.THROW_EXCEPTION) {
                throw new RuntimeException("任务已在执行中");
            } else if (method == FallBackMethod.WAIT) {
                lock.lock();
            } else if (method == FallBackMethod.SKIP_UNTIL_PROCESSED) {
                lock.lock();
                lock.unlock();
            } else if (method == FallBackMethod.SKIP_IMMEDIATELY) {
                return;
            } else {
                throw new RuntimeException("未知的FallBackMethod");
            }
        }

        try {
            processor.run();
        } finally {
            lock.unlock();
        }
    }

    public enum FallBackMethod {
        THROW_EXCEPTION, WAIT, SKIP_UNTIL_PROCESSED, SKIP_IMMEDIATELY
    }
}
