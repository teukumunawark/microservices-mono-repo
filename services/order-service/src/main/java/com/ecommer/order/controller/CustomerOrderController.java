package com.ecommer.order.controller;

import com.ecommer.order.model.request.CustomerOrderRequest;
import com.ecommer.order.model.response.CustomerOrderResponse;
import com.ecommer.order.service.CreateCustomerOrderService;
import com.ecommer.order.service.FindAllCustomerOrderService;
import com.ecommer.order.service.FindCustomerOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class CustomerOrderController {
    private final CreateCustomerOrderService createOrderService;
    private final FindAllCustomerOrderService findAllCustomerOrderService;
    private final FindCustomerOrderService findCustomerOrderService;

    @PostMapping
    public ResponseEntity<Integer> create(
            @RequestBody @Valid CustomerOrderRequest request
    ) {
        return ResponseEntity.ok(createOrderService.execute(request));
    }

    @GetMapping
    public ResponseEntity<List<CustomerOrderResponse>> findAll() {
        return ResponseEntity.ok(findAllCustomerOrderService.execute());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<CustomerOrderResponse> findById(
            @PathVariable("order-id") Integer orderId
    ) {
        return ResponseEntity.ok(findCustomerOrderService.execute(orderId));
    }
}
