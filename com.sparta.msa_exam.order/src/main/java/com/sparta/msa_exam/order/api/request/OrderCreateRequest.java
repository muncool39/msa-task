package com.sparta.msa_exam.order.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record OrderCreateRequest(
        @NotBlank String description,
        @NotNull List<ProductAddRequest> product_ids
) {
}
