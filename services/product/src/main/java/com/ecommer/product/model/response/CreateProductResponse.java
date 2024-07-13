package com.ecommer.product.model.response;

import lombok.Builder;

@Builder
public record CreateProductResponse(
        Integer id,
        String message
) implements BaseDataResponse {
}
