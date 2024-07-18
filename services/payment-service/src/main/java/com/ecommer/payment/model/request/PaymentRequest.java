package com.ecommer.payment.model.request;

import com.ecommer.payment.model.enumerated.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerRequest customer
) {
}
