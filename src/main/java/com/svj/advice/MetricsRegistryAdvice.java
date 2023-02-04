package com.svj.advice;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MetricsRegistryAdvice {

    private ObservationRegistry registry;

    public MetricsRegistryAdvice(ObservationRegistry registry){
        this.registry= registry;
    }

//    @After(value = "execution(* com.svj.controller.ProductController.*(..))")
//    public void sendMetrics(JoinPoint joinPoint){
//        log.info("Application collecting metrics");
//        Observation.createNotStarted(joinPoint.getSignature().getName(), registry)
//                .observe(()->joinPoint.getArgs());
//        log.info("Application published the metrics");
//    }
}
