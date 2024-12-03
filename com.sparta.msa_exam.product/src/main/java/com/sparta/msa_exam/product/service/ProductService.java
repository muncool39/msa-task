package com.sparta.msa_exam.product.service;


import com.sparta.msa_exam.product.api.request.ProductCreateRequest;
import com.sparta.msa_exam.product.api.response.ProductResponse;
import com.sparta.msa_exam.product.domain.Product;
import com.sparta.msa_exam.product.exception.ProductErrorCode;
import com.sparta.msa_exam.product.exception.ProductException;
import com.sparta.msa_exam.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    @CacheEvict(cacheNames = "productListCache", allEntries = true)
    public void createProduct(ProductCreateRequest request) {
        if(isNameDuplicated(request.name())) {
            throw new ProductException(ProductErrorCode.PRODUCT_ALREADY_EXIST);
        }
        Product.create(
                request.name(),
                request.supply_price()
        );
    }

    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->
                new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND)
        );
        return ProductResponse.of(product);
    }

    @Cacheable(cacheNames = "productListCache", key = "methodName")
    public List<ProductResponse> getProductList() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponse::of).toList();
    }

    private boolean isNameDuplicated(String productName) {
        return productRepository.existsByName(productName);
    }

}
