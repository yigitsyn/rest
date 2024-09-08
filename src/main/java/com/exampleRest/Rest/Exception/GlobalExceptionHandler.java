package com.exampleRest.Rest.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(StudentIDZeroException.class);

    @ExceptionHandler(StudentIDZeroException.class)
    public ResponseEntity<String> handleStudentIdZeroException(StudentIDZeroException ex){
        logger.error(ex.getMessage(),ex.getStackTrace());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
