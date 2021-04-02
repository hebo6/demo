package com.example.demo.aop;



import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.*;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;



import java.lang.reflect.Method;



@Aspect

@Component

@Slf4j

public class AopAspect {



    @Pointcut("@annotation(com.example.demo.aop.AopAnnotation)")

    public void myPointcut() {

    }



    @Before("myPointcut()")

    public void doBefore(JoinPoint joinPoint) throws Exception {

        String description = getDescription(joinPoint);

        log.info("Before Aop， value = " + description);

    }



    @After("myPointcut()")

    public void doAfter(JoinPoint joinPoint) throws Exception {

        String description = getDescription(joinPoint);

        log.info("After Aop， value = " + description);

    }



    @Around(value = "myPointcut()", argNames = "pjp")

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        pjp.proceed();

        log.info("Around Aop");

        return null;

    }



    private String getDescription(JoinPoint joinPoint) throws Exception {

        String targetName = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        Object[] arguments = joinPoint.getArgs();

        Class<?> targetClass = Class.forName(targetName);

        Method[] methods = targetClass.getMethods();

        StringBuilder description = new StringBuilder();

        for (Method method : methods) {

            if (method.getName().equals(methodName)) {

                Class<?>[] classes = method.getParameterTypes();

                if (classes.length == arguments.length) {

                    description.append(method.getAnnotation(AopAnnotation.class).value());

                    break;

                }

            }

        }

        return description.toString();

    }

}


