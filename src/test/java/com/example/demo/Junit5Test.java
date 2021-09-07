package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.opentest4j.AssertionFailedError;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@DisplayName("Junit5测试")
public class Junit5Test {

    @BeforeEach
    void testBeforeEach() {
        System.out.println("测试就要开始了");
    }

    @AfterEach
    void testAfterEach() {
        System.out.println("测试结束了");
    }

    @BeforeAll
    static void testBeforeAll() {
        System.out.println("所有测试就要开始了");
    }

    @AfterAll
    static void testAfterAll() {
        System.out.println("所有测试结束了");
    }

    @DisplayName("禁用的测试")
    @Disabled
    @Test
    void testDisabledTest() {
    }

    @DisplayName("断言示例")
    @Test
    void demonstrateAssertionExample() {
        Assumptions.assumeTrue(true, "前置条件不满足，终止运行");

        Assertions.assertTrue(true, "计算错误");
        Assertions.assertNull(null, "计算错误");
        Assertions.assertEquals(1, 1, "计算错误");
        Assertions.assertSame(1, 1, "计算错误");
        Assertions.assertThrows(AssertionFailedError.class, () -> Assertions.fail("计算错误"), "计算错误");

        //断言每个索引处的元素是否相等
        Assertions.assertIterableEquals(List.of(1, 2, 3), List.of(1, 2, 3), "计算错误");
        Assertions.assertAll("断言all", () -> {
        }, () -> {
        });
        Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(100), "计算错误");
    }

    @DisplayName("重复测试")
    @RepeatedTest(3)
    void testRepeatedTest() throws InterruptedException {
        int millis = new Random().nextInt(2) * 1000;
        Thread.sleep(millis);
    }

    @DisplayName("超时测试")
    @Timeout(2)
    void testTimeOut() throws InterruptedException {
        int millis = new Random().nextInt(2) * 1000;
        Thread.sleep(millis);
    }

    @DisplayName("参数化测试-单个参数")
    @ParameterizedTest
    @ValueSource(strings = {"str0", "str1"})
    void testParameterizedTest(String s) {
        System.out.println("s = " + s);
    }

    @DisplayName("参数化测试-empty参数")
    @ParameterizedTest
    @EmptySource
    void testEmptySource(String s) {
        System.out.println("s = " + s);
        Assertions.assertEquals("", s);
    }

    @DisplayName("参数化测试-null参数")
    @ParameterizedTest
    @NullSource
    void testNullSource(String s) {
        System.out.println("s = " + s);
        Assertions.assertNull(s);
    }

    @DisplayName("参数化测试-空参数")
    @ParameterizedTest
    @NullAndEmptySource
    void testNullAndEmptySource(String s) {
        System.out.println("s = " + s);
        Assertions.assertTrue(StringUtils.isEmpty(s));
    }

    /**
     * 对于@CsvSource, 空字符串用2个单引号表示'', null用空白表示
     */
    @DisplayName("参数化测试-多个参数")
    @ParameterizedTest
    @CsvSource({"a,1", "'',1", ",1"})
    void testMultiValuesParameterizedTest(String s, boolean b) {
        System.out.println("s = " + s);
        System.out.println("b = " + b);
    }
}
