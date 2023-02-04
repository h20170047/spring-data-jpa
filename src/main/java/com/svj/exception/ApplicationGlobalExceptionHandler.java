package com.svj.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationGlobalExceptionHandler {
    @ExceptionHandler(InvalidInput.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleBadInput(InvalidInput invalidInput){
        return new ResponseEntity(invalidInput.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
