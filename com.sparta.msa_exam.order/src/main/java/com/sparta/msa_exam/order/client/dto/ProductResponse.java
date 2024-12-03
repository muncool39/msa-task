package com.sparta.msa_exam.order.client.dto;

public record ProductResponse(
        Long product_id,
        String name,
        Integer supply_price
) {
}
