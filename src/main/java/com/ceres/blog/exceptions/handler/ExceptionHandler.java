package com.ceres.blog.exceptions.handler;

import com.ceres.blog.exceptions.exception.UserExistsException;
import com.ceres.blog.exceptions.response.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(UserExistsException.class)
    private final ResponseEntity<Object> userExists(UserExistsException e){
        ExceptionMessage exceptionMessage = new ExceptionMessage(
                e.getMessage(),
                HttpStatus.CONFLICT,
                new Timestamp(System.currentTimeMillis())
        );
        return new ResponseEntity<>(exceptionMessage,HttpStatus.CONFLICT);
    }
}
