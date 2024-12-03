package com.sparta.msa_exam.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OrderErrorCode {

    FALL_BACK(HttpStatus.INTERNAL_SERVER_ERROR, "잠시 후에 주문 추가를 요청 해주세요"),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 주문 정보가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
