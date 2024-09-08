package com.exampleRest.Rest.Exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class StudentIDZeroException extends RuntimeException{
    public StudentIDZeroException(String message) {
        super(message);
    }
}
