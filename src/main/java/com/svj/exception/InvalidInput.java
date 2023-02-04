package com.svj.exception;

public class InvalidInput extends RuntimeException {
    public InvalidInput(String errorMessage) {
        super(errorMessage);
    }
}
