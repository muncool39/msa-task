package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.api.request.ProductAddRequest;
import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.domain.Order;

import com.sparta.msa_exam.order.domain.OrderProduct;
import com.sparta.msa_exam.order.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderProductService {
    private final ProductClient productClient;
    private final OrderProductRepository orderProductRepository;

    public void addProduct(Order order, ProductAddRequest request) {
        Long productId = getValidProductId(request.product_id());
        orderProductRepository.save(OrderProduct.create(order, productId));
    }

    private Long getValidProductId(Long productId) {
        return productClient.getProduct(productId).product_id();
    }

}
