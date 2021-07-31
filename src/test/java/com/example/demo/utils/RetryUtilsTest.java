package com.example.demo.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RetryUtilsTest {

    private int thrownTimes = 0;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void retryable(final int times) {
        int maxTimes = 3;
        if (maxTimes >= times) {
            String r = RetryUtils.retryable(() -> throwEx(times), maxTimes);
            Assertions.assertEquals("ok", r);
        } else {
            Assertions.assertThrows(RuntimeException.class, () -> RetryUtils.retryable(() -> throwEx(times), maxTimes));
        }
    }

    private String throwEx(int times) {
        if (++this.thrownTimes < times) {
            throw new RuntimeException("抛异常");
        }
        return "ok";
    }
}
