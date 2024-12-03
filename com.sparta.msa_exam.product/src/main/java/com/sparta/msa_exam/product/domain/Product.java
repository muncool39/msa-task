package com.sparta.msa_exam.product.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private String name;
    private Integer supply_price;

    @Builder(access = AccessLevel.PRIVATE)
    private Product(String name, Integer supply_price) {
        this.name = name;
        this.supply_price = supply_price;
    }

    public static void create(String name, int supply_price) {
        Product.builder()
                .name(name)
                .supply_price(supply_price)
                .build();
    }

}
