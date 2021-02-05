package com.example.aop;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface AopAnnotation {
    /**
     * 描述
     */
    String value() default "";
}
