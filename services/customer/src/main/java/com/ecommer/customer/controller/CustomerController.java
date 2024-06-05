package com.ecommer.customer.controller;

import com.ecommer.customer.model.request.CustomerRequest;
import com.ecommer.customer.model.response.CustomerResponse;
import com.ecommer.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        return ResponseEntity.ok(this.service.getCustomers());
    }

    @GetMapping("/exits/{customer-id}")
    public ResponseEntity<Boolean> exitsById(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(this.service.exitsById(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(this.service.getCustomerById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomerById(
            @PathVariable("customer-id") String customerId
    ) {
        this.service.deleteCustomerById(customerId);
        return ResponseEntity.accepted().build();
    }
}
