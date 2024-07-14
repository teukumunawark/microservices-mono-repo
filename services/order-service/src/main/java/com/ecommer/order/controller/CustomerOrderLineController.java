package com.ecommer.order.controller;

import com.ecommer.order.model.response.CustomerOrderLineResponse;
import com.ecommer.order.service.CustomerOrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class CustomerOrderLineController {

    private final CustomerOrderLineService service;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<CustomerOrderLineResponse>> findByOrderId(
            @PathVariable("order-id") Integer orderId
    ) {
        return ResponseEntity.ok(service.findAllByOrderId(orderId));
    }
}
