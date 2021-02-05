package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class AopAspect {
    private static final Logger log = LoggerFactory.getLogger(AopAspect.class);

    @Pointcut("@annotation(com.example.aop.AopAnnotation)")
    public void myPointcut() {
    }

    @Before("myPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        String description = getDescription(joinPoint);
        log.info("doBefore executed， value = " + description);
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
