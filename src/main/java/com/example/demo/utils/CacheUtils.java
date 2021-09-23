package com.example.demo.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.map.LRUMap;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheUtils {
    private static final int maxSize = 10_000;
    private static final Map<Object, Object> cache = Collections.synchronizedMap(new LRUMap<>(maxSize));

    public static Object get(Function<Object, Object> generator, Object parameter) {
        return cache.computeIfAbsent(parameter, generator);
    }
}
