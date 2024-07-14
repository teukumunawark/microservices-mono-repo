package com.ecommer.order.service;

import com.ecommer.order.model.response.CustomerOrderResponse;
import com.ecommer.order.repository.CustomerOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllCustomerOrderService {

    private final CustomerOrderRepository repository;

    public List<CustomerOrderResponse> execute() {
        return repository.findAll()
                .stream()
                .map(customerOrder -> CustomerOrderResponse.builder()
                        .id(customerOrder.getId())
                        .customerId(customerOrder.getCustomerId())
                        .reference(customerOrder.getReference())
                        .paymentMethod(customerOrder.getPaymentMethod())
                        .amount(customerOrder.getTotalAmount())
                        .build()
                ).collect(Collectors.toList());

    }
}
