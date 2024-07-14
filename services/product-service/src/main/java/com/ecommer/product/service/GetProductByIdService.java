package com.ecommer.product.service;

import com.ecommer.product.model.response.GetProductResponse;
import com.ecommer.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductByIdService {

    final ProductRepository repository;

    public GetProductResponse execute(Integer productId) {
        return repository.findById(productId)
                .map(product -> GetProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .availableQuantity(product.getAvailableQuantity())
                        .categoryId(product.getCategory().getId())
                        .categoryName(product.getCategory().getName())
                        .categoryDescription(product.getCategory().getDescription())
                        .build())
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
