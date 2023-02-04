package com.svj.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    private ObjectMapper objectMapper= new ObjectMapper();
    @PostConstruct
    public void setup(){
        objectMapper.registerModule(new JavaTimeModule());
    }

//    @Pointcut("execution(* com.svj.*.*.*(..))") //All public methods of com.svj package, every subpackage, every class, every method, irrepective of any type of arguments
//    @Pointcut("within(com.svj..*)") // wouldnt print logs for repository as it is an interface
//    @Pointcut("target(com.svj.service.ProductService)")
//    @Pointcut("execution(* com.svj.service.ProductService.save*(..))")
    @Pointcut("execution(* com.svj.controller.ProductController.*(..)) || " +
            "execution(* com.svj.service.ProductService.*(..))")
    private void logPointCut(){
    }

    @Before("logPointCut()")
    public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("Class name {}, Method name {}", joinPoint.getTarget(), joinPoint.getSignature().getName());
        log.info("Request Body {}", objectMapper.writeValueAsString(joinPoint.getArgs()));
    }
}
