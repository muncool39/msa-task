package com.sparta.msa_exam.auth.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthCustomException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public AuthCustomException(AuthErrorCode errorCode) {
        this.httpStatus = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }
}
