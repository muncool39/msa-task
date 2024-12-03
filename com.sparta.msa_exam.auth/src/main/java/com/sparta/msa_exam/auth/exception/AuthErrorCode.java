package com.sparta.msa_exam.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode {
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호 정보가 일치하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 유저 정보가 존재하지 않습니다."),
    ID_ALREADY_EXIST(HttpStatus.CONFLICT, ".");

    private final HttpStatus httpStatus;
    private final String message;
}
