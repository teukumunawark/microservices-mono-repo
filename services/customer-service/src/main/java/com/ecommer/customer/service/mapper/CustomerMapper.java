package com.ecommer.customer.service.mapper;

import com.ecommer.customer.model.entity.Address;
import com.ecommer.customer.model.entity.Customer;
import com.ecommer.customer.model.request.CustomerRequest;
import com.ecommer.customer.model.response.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(Address.builder()
                        .street(request.address().street())
                        .hostNumber(request.address().hostNumber())
                        .postalCode(request.address().postalCode())
                        .build())
                .build();
    }

    public CustomerResponse fromCostumer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                CustomerResponse.AddressResponse.builder()
                        .street(customer.getAddress().getStreet())
                        .hostNumber(customer.getAddress().getHostNumber())
                        .postalCode(customer.getAddress().getPostalCode())
                        .build()
        );
    }
}
