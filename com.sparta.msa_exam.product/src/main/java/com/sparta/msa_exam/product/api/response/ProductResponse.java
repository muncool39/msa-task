package com.sparta.msa_exam.product.api.response;

import com.sparta.msa_exam.product.domain.Product;

public record ProductResponse(
        Long product_id,
        String name,
        Integer supply_price
) {
    public static ProductResponse of(Product product) {
        return new ProductResponse(
                product.getProduct_id(),
                product.getName(),
                product.getSupply_price()
        );
    }
}
