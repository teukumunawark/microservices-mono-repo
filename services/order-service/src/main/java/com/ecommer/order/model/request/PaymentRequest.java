package com.ecommer.order.model.request;

import com.ecommer.order.model.enumerated.PaymentMethod;
import com.ecommer.order.model.response.CustomerRequest;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerRequest customer
) {
}
