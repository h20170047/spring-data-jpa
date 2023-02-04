package com.svj.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionAlertAdvice {

    @Pointcut("execution(* com.svj.controller.ProductController.*(..))")
    public void alertFor(){

    }

    @AfterThrowing(value = "alertFor()", throwing = "exception")
    public void captureErrorAndSetAlerts(JoinPoint joinPoint, Exception exception){
        log.error("Exception occured in {} ", joinPoint.getSignature());
        log.error("Exception message {}", exception.getMessage());
    }
}
