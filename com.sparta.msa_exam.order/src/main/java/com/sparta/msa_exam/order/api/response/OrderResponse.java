package com.sparta.msa_exam.order.api.response;

import com.sparta.msa_exam.order.domain.Order;
import com.sparta.msa_exam.order.domain.OrderProduct;
import java.util.List;

public record OrderResponse(
        Long order_id,
        List<Long> product_ids
) {
    public static OrderResponse of(Order order) {
        return new OrderResponse(
                order.getOrder_id(),
                order.getOrderProducts().stream().map(OrderProduct::getId).toList()
        );
    }
}
