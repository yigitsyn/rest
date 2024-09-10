package com.exampleRest.Rest.Exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class StudentIDZeroException extends RuntimeException{
    public StudentIDZeroException(String message) {
        super(message);
    }
}
