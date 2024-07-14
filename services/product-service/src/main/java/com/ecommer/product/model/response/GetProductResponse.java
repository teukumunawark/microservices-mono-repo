package com.ecommer.product.model.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record GetProductResponse(
        Integer id,
        String name,
        String description,
        Double availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) implements Response {
}
