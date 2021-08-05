package com.example.demo.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

class BatchUtilsTest {
    static int batchCount = 0;
    final static int batchSize = 100;

    @BeforeEach
    void setUp() {
        batchCount = 0;
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 10000, 100000, 5, 55, 555, 5555, 5555})
    void batchProcess(int total) {
        List<Integer> feeds = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            feeds.add(i);
        }

        List<String> r = BatchUtils.batchProcess(feeds, BatchUtilsTest::processor, batchSize);

        Assertions.assertIterableEquals(feeds.stream().map(String::valueOf).toList(), r, "处理的结果不错误");
        Assertions.assertEquals((total - 1) / batchSize + 1, batchCount, "批量执行次数错误");
    }

    static List<String> processor(List<Integer> s) {
        Assertions.assertTrue(s.size() <= batchSize);
        batchCount++;
        return s.stream().map(String::valueOf).toList();
    }
}