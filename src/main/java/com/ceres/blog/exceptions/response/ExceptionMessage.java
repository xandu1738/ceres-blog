package com.ceres.blog.exceptions.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {
    private String message;
    private HttpStatus httpStatus;
    private Timestamp timestamp;
}
