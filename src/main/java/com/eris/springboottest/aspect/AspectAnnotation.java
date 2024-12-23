package com.eris.springboottest.aspect;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Aspect
@Component
@Slf4j
@Order(0)
public class AspectAnnotation {

    private ExecutorService pool = Executors.newFixedThreadPool(5);

    @Pointcut("@annotation(myAnnotation)")
    public void annotationPointcut(MyAnnotation myAnnotation) {
    }

    @Around("annotationPointcut(myAnnotation)")
    public Object around(ProceedingJoinPoint pjp, MyAnnotation myAnnotation) throws Throwable {
        //获取请求方法
        Signature sig = pjp.getSignature();
        String method = pjp.getTarget().getClass().getName() + "." + sig.getName();
        //获取请求的参数
        Object[] args = pjp.getArgs();

        //打印请求参数
        log.info("参数:" + method + ":" + JSON.toJSONString(args));

        Object result = pjp.proceed();

        log.info("返回:" + JSON.toJSONString(result));
        return result;
    }

}
