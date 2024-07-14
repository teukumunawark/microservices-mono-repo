package com.ecommer.order.model.request;

import lombok.Builder;

@Builder
public record CustomerOrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity
) {
}
