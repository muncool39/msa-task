package com.sparta.msa_exam.order.api.request;

import jakarta.validation.constraints.NotNull;

public record ProductAddRequest(
        @NotNull Long product_id
) {
}
