package com.ecommer.customer.model.response;

import lombok.Builder;

@Builder
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        AddressResponse address
) {
    @Builder
    public record AddressResponse(
            String street,
            String hostNumber,
            String postalCode
    ) {
    }
}
