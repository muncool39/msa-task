package com.sparta.msa_exam.product.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequest(
        @NotBlank String name,
        @NotNull Integer supply_price
) {
}
