package com.sparta.springcore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 모든 RestController에 에러요청을 받는다. (스프링 aop를 이용하여 이렇게 따로 선언해놔도 모든 기능에 부가기능으로 추가할수 있도록 한다.)
public class ApiExceptionHandler {

    @ExceptionHandler(value = { ApiRequestException.class })
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                // HTTP 400 -> Client Error
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(
                apiException,
                // HTTP 400 -> Client Error
                HttpStatus.BAD_REQUEST
        );
    }
}