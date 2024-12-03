package com.sparta.msa_exam.auth.api.request;

import jakarta.validation.constraints.NotBlank;

public record SignInRequest(
        @NotBlank String user_id,
        @NotBlank String password
) {
}
