package com.sparta.msa_exam.product.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductErrorCode {

    PRODUCT_ALREADY_EXIST(HttpStatus.CONFLICT, "이미 존재하는 상품입니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 상품 정보가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
