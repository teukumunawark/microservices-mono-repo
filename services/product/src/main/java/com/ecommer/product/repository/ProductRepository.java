package com.ecommer.product.repository;

import com.ecommer.product.model.entity.Product;
import com.ecommer.product.model.response.ProductPurchaseResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}
