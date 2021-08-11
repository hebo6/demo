package com.example.demo.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BatchUtils {

    private static final int defaultBatchSize = 500;

    public static <K, V> List<V> batchProcess(List<K> feeds, Function<List<K>, List<V>> processor) {
        return batchProcess(feeds, processor, defaultBatchSize);
    }

    public static <K, V> List<V> batchProcess(List<K> feeds, Function<List<K>, List<V>> processor, int batchSize) {
        List<V> r = new ArrayList<>();
        if (CollectionUtils.isEmpty(feeds)) {
            return r;
        }
        List<List<K>> partition = ListUtils.partition(feeds, batchSize);
        for (List<K> feedsPart : partition) {
            //新建 tmp, 为了防止因 processor.apply() 的调用中对集合参数进行了迭代, 而抛异常 java.util.NoSuchElementException
            List<K> tmp = new ArrayList<>(feedsPart);
            List<V> rPart = processor.apply(tmp);
            if (CollectionUtils.isNotEmpty(rPart)) {
                r.addAll(rPart);
            }
        }
        return r;
    }

    public static <K> void batchConsume(List<K> feeds, Consumer<List<K>> consumer) {
        batchConsume(feeds, consumer, defaultBatchSize);
    }

    public static <K> void batchConsume(List<K> feeds, Consumer<List<K>> consumer, int batchSize) {
        if (CollectionUtils.isEmpty(feeds)) {
            return;
        }
        List<List<K>> partition = ListUtils.partition(feeds, batchSize);
        for (List<K> feedsPart : partition) {
            //新建 tmp, 为了防止因 consumer.accept() 的调用中对集合参数进行了迭代, 而抛异常 java.util.NoSuchElementException
            List<K> tmp = new ArrayList<>(feedsPart);
            consumer.accept(tmp);
        }
    }
}
