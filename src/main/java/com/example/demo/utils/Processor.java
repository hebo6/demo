package com.example.demo.utils;

/**
 * 没有参数,没有返回职的函数式接口.
 * 如果使用Runnable接口,会有语义上的歧义,因为Runnable不仅仅是函数接口,还有线程上的语义.
 */
@FunctionalInterface
public interface Processor {
    void run();

    default Processor andThen(Processor after) {
        return () -> {
            this.run();
            after.run();
        };
    }

    default Processor compose(Processor before) {
        return () -> {
            before.run();
            this.run();
        };
    }
}
