package com.ecommer.customer.service;

import com.ecommer.customer.error.exception.CostumerNotFoundException;
import com.ecommer.customer.model.entity.Address;
import com.ecommer.customer.model.request.CustomerRequest;
import com.ecommer.customer.model.response.CustomerResponse;
import com.ecommer.customer.repository.CustomerRepository;
import com.ecommer.customer.service.mapper.CustomerMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var costumer = repository.save(mapper.toCustomer(request));
        return costumer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var costumer = repository.findById(request.id())
                .orElseThrow(() -> new CostumerNotFoundException(format("Customer with id %s not found", request.id())));
        if (StringUtils.isNotBlank(request.firstName())) {
            costumer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            costumer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            costumer.setEmail(request.email());
        }
        if (request.address() != null) {
            costumer.setAddress(Address.builder()
                    .street(request.address().street())
                    .hostNumber(request.address().hostNumber())
                    .postalCode(request.address().hostNumber())
                    .build());
        }
        repository.save(costumer);
    }

    public List<CustomerResponse> getCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCostumer)
                .toList();
    }

    public Boolean exitsById(String customerId) {
        return repository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse getCustomerById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCostumer)
                .orElseThrow(() -> new CostumerNotFoundException(String.format("Customer with id %s not found", customerId)));
    }

    public void deleteCustomerById(String customerId) {
        repository.deleteById(customerId);
    }
}
