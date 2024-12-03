package com.sparta.msa_exam.product.repository;

import com.sparta.msa_exam.product.domain.Product;
import java.util.List;

public interface ProductCustomRepository {
    List<Product> findAllProductsWith(String name);
}
