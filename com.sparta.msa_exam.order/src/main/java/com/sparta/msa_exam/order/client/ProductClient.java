package com.sparta.msa_exam.order.client;


import com.sparta.msa_exam.order.client.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/products/{productId}")
    ProductResponse getProduct(@PathVariable Long productId);
}
