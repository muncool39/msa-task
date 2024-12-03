package com.sparta.msa_exam.order.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    private Long productId;

    @Builder(access = AccessLevel.PRIVATE)
    private OrderProduct(Order order, Long productId) {
        this.order = order;
        this.productId = productId;
    }

    public static OrderProduct create(Order order, Long productId) {
        return OrderProduct.builder()
                .order(order)
                .productId(productId)
                .build();
    }

}
