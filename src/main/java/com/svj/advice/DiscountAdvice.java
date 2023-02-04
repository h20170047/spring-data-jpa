package com.svj.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svj.entity.Product;
import com.svj.entity.RequestObj;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DiscountAdvice {
    private ObjectMapper objectMapper;
    public DiscountAdvice(ObjectMapper objectMapper){
        this.objectMapper= new ObjectMapper();
    }

    @AfterReturning(value = "execution(* com.svj.controller.ProductController.saveProduct(com.svj.entity.RequestObj))")
    public void addDiscount(JoinPoint joinPoint){
        Product savedProduct = objectMapper.convertValue(((RequestObj)joinPoint.getArgs()[0]).getPayload(), Product.class);
        double discount= 0;
        if(savedProduct.getPrice()> 20000){
            discount= savedProduct.getPrice()*.3;
        }else if(savedProduct.getPrice()> 10000){
            discount= savedProduct.getPrice()*.2;
        }else if(savedProduct.getPrice()> 5000){
            discount= savedProduct.getPrice()*.1;
        }
        if(discount>0){
            log.info("Congrats on big purchase. You have earned a discount of {}. Final bill amount is {}", discount, savedProduct.getPrice()- discount);
        }
    }
}
