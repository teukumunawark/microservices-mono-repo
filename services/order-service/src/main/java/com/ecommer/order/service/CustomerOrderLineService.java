package com.ecommer.order.service;

import com.ecommer.order.model.entity.CustomerOrder;
import com.ecommer.order.model.entity.CustomerOrderLine;
import com.ecommer.order.model.request.CustomerOrderLineRequest;
import com.ecommer.order.model.response.CustomerOrderLineResponse;
import com.ecommer.order.repository.CustomerOrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerOrderLineService {
    private final CustomerOrderLineRepository repository;

    public void saveCustomerOrderLine(CustomerOrderLineRequest request) {
        var order = CustomerOrderLine.builder()
                .productId(request.productId())
                .customerOrder(CustomerOrder.builder()
                        .id(request.orderId())
                        .build())
                .quantity(request.quantity())
                .build();

        repository.save(order);
    }

    public List<CustomerOrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByCustomerOrderId(orderId)
                .stream()
                .map(customer -> CustomerOrderLineResponse.builder()
                        .id(customer.getId())
                        .quantity(customer.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }
}
