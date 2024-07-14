package com.ecommer.order.model.response;

import lombok.Builder;

@Builder
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
