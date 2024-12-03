package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.api.request.OrderCreateRequest;
import com.sparta.msa_exam.order.api.request.ProductAddRequest;
import com.sparta.msa_exam.order.api.response.OrderResponse;
import com.sparta.msa_exam.order.domain.Order;
import com.sparta.msa_exam.order.exception.OrderErrorCode;
import com.sparta.msa_exam.order.exception.OrderException;
import com.sparta.msa_exam.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderProductService orderProductService;
    private final OrderRepository orderRepository;

    @Transactional
    @CircuitBreaker(name = "order", fallbackMethod = "fallbackCreateOrder")
    public void createOrder(OrderCreateRequest request, Boolean fail) {
        if(fail) {
            throw new OrderException(OrderErrorCode.FALL_BACK);
        }
        Order order = orderRepository.save(Order.create(request.description()));
        request.product_ids().forEach(product_id ->
                orderProductService.addProduct(order, product_id)
        );
    }

    @Transactional
    public void addProduct(Long orderId, ProductAddRequest request) {
        Order order = getOrderOrException(orderId);
        orderProductService.addProduct(order, request);
    }

    @Cacheable(cacheNames = "orderCache", key = "args[0]")
    public OrderResponse getOrder(Long orderId) {
        Order order = getOrderOrException(orderId);
        return OrderResponse.of(order);
    }

    public void fallbackCreateOrder() {
        throw new OrderException(OrderErrorCode.FALL_BACK);
    }

    private Order getOrderOrException(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(()->
                new OrderException(OrderErrorCode.ORDER_NOT_FOUND)
        );
    }




}
