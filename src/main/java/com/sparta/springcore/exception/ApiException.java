package com.sparta.springcore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApiException { // 에러 new throw 이렇게 해도 클라이언트로 메세지가 안가기때문에 에러처리하는 부분을 따로만든다.
    private final String message;
    private final HttpStatus httpStatus;
}