package com.sparta.msa_exam.product.api;

import com.sparta.msa_exam.product.api.request.ProductCreateRequest;
import com.sparta.msa_exam.product.api.response.ProductResponse;
import com.sparta.msa_exam.product.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public void createProduct(@Valid ProductCreateRequest request) {
        productService.createProduct(request);
    }

    @GetMapping
    public List<ProductResponse> getProductList() {
        return productService.getProductList();
    }

    @GetMapping("/{productId}")
    public ProductResponse getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

}
