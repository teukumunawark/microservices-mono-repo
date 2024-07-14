package com.ecommer.order.service;

import com.ecommer.order.model.response.CustomerOrderResponse;
import com.ecommer.order.repository.CustomerOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindCustomerOrderService {

    private final CustomerOrderRepository repository;

    public CustomerOrderResponse execute(Integer orderId) {
        return repository.findById(orderId)
                .map(customerOrder -> CustomerOrderResponse.builder()
                        .id(customerOrder.getId())
                        .customerId(customerOrder.getCustomerId())
                        .reference(customerOrder.getReference())
                        .paymentMethod(customerOrder.getPaymentMethod())
                        .amount(customerOrder.getTotalAmount())
                        .build())
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));
    }
}
