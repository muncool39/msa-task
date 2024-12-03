package com.sparta.msa_exam.auth.exception;

public record ExceptionResponse(
        String message
) {
    public String toWrite() {
        return "{" +
                "\"message\":" + "\"" + message +"\"" +
                "}";
    }
}
