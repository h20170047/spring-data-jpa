package com.svj.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CustomLoggingFramework {
    private ObjectMapper objectMapper;
    public CustomLoggingFramework(ObjectMapper objectMapper){
        this.objectMapper= objectMapper;
    }

    // before and after returning
    @Around("execution (* com.svj.controller.ProductController.*(..))")
    public Object captureRequestAndResponse(ProceedingJoinPoint pjp) throws Throwable {
        // execute your logic with request before calling proceed()
        System.out.println("====================BEFORE START============================");
        log.info("Execution started {}", pjp.getSignature());
        log.info("Request body {}", objectMapper.writeValueAsString(pjp.getArgs()));
        System.out.println("====================BEFORE END============================");

        Object obj = pjp.proceed();
        System.out.println("====================AFTER START============================");
        log.info("Execution ended {}", pjp.getSignature());
        log.info("Response body {}", objectMapper.writeValueAsString(obj));
        System.out.println("====================AFTER END============================");
        // process response
        return obj;
    }
}
