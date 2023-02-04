package com.svj.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogExecutionTracker {

    @Around("@annotation(com.svj.annotation.TrackExecutionTime)")
    public Object logExecutionDuration(ProceedingJoinPoint pjp) throws Throwable {
        long startTime= System.currentTimeMillis();
        Object obj = pjp.proceed();
        long endTime= System.currentTimeMillis();
        log.info("Method {} executed in {} ms", pjp.getSignature(), (endTime-startTime));
        return obj;
    }
}
