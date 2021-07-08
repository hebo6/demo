package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 执行顺序是
 * <code>
 * Around(){
 * proceed(){
 * Before();
 * exec();
 * After();
 * }
 * }
 * </code>
 */
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

    @AfterReturning("myPointcut()")
    public void doAfterReturning(JoinPoint joinPoint) throws Exception {
        String description = getDescription(joinPoint);
        log.info("AfterReturning Aop， value = " + description);
    }

    @AfterThrowing("myPointcut()")
    public void doAfterThrowing(JoinPoint joinPoint) throws Exception {
        String description = getDescription(joinPoint);
        log.info("AfterThrowing Aop， value = " + description);
    }

    @Around(value = "myPointcut()", argNames = "pjp")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Around Aop start");
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                args[i] += "(Aop proceed arg)";
            }
        }
        //实际执行的真实方法
        Object proceed = pjp.proceed(args);
        log.info("Around Aop end");
        return proceed + "(Aop proceed result)";
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
