package com.ecommer.order.model.response;

import lombok.Builder;

@Builder
public record CustomerOrderLineResponse(
        Integer id,
        double quantity
) {
}
