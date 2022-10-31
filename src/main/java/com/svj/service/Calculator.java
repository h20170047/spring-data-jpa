package com.svj.service;

public class Calculator {
    public static int sum(int a, int b) {
        if(a<0 || b<0){
            throw new RuntimeException("Negative inputs are not allowed in sum");
        }
        return a+b;
    }
}
