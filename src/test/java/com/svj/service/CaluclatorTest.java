package com.svj.service;

import org.hibernate.jdbc.Expectation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CaluclatorTest {

    @Test
    public void testAddition(){
        int a= 10, b= 8;
        int actualResult= Calculator.sum(a,b);
        assertThat(actualResult).isEqualTo(18);
    }

    @Test
    public void testSumForNegativeValues(){
        int a= 18, b= -10;
        assertThrows(RuntimeException.class, ()-> Calculator.sum(a, b));

    }
}
