package com.sparta.msa_exam.order.api;


import com.sparta.msa_exam.order.api.request.OrderCreateRequest;
import com.sparta.msa_exam.order.api.request.ProductAddRequest;
import com.sparta.msa_exam.order.api.response.OrderResponse;
import com.sparta.msa_exam.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void createOrder(
            @Valid @RequestBody OrderCreateRequest request,
            @RequestParam(value = "fail", defaultValue = "false") Boolean fail
    ) {
        orderService.createOrder(request, fail);
    }

    @PutMapping("/{orderId}")
    public void addProduct(
            @PathVariable Long orderId,
            @Valid @RequestBody ProductAddRequest request
    ) {
        orderService.addProduct(orderId, request);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

}
