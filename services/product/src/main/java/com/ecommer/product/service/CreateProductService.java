package com.ecommer.product.service;

import com.ecommer.product.model.entity.Category;
import com.ecommer.product.model.entity.Product;
import com.ecommer.product.model.request.CreateProductRequest;
import com.ecommer.product.model.response.CreateProductResponse;
import com.ecommer.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreateProductService {
    private final ProductRepository repository;

    public CreateProductResponse execute(CreateProductRequest request) {
        var product = repository.save(
                Product.builder()
                        .name(request.name())
                        .price(request.price())
                        .description(request.description())
                        .availableQuantity(request.availableQuantity())
                        .category(Category.builder()
                                .id(request.categoryId())
                                .build())
                        .build()
        );

        return CreateProductResponse.builder()
                .id(product.getId())
                .message("Successfully created product")
                .build();
    }
}
