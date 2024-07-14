package com.ecommer.product.service;

import com.ecommer.product.model.response.GetProductResponse;
import com.ecommer.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductService {

    private final ProductRepository repository;

    public List<GetProductResponse> execute() {
        return repository.findAll()
                .stream()
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
                .toList();
    }
}
