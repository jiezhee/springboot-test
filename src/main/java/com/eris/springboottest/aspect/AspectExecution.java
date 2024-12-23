package com.eris.springboottest.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(0)
public class AspectExecution {

    @Pointcut("execution(* com.eris.springboottest.aspect.Target.test(..))")
    private void myPointCut() {
    }

    @Before("myPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("========切面前置通知");
    }

    @Around("myPointCut()")
    public Object aroundExec(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("========切面环绕前置处理");
        Object proceed = joinPoint.proceed();
        System.out.println("========切面环绕后置处理");
        return proceed;
    }

    @AfterReturning(value = "myPointCut()")
    public void doAfterReturning() {
        System.out.println("========切面doAfterReturning后置通知");
    }

    @After("myPointCut()")
    public void doAfter() {
        System.out.println("========切面doAfter最终通知");
    }

    @AfterThrowing(value = "myPointCut()", throwing = "e")
    public void doThrow(Exception e) {
        System.out.println("========切面异常通知:" + e.getMessage());
    }

}

