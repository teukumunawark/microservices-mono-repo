package com.ecommer.product.service;

import com.ecommer.product.error.exception.ProductPurchaseException;
import com.ecommer.product.model.entity.Product;
import com.ecommer.product.model.request.ProductPurchaseRequest;
import com.ecommer.product.model.response.ProductPurchaseResponse;
import com.ecommer.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateProductPurchaseService {

    private final ProductRepository repository;

    public List<ProductPurchaseResponse> execute(List<ProductPurchaseRequest> requests) {
        List<Integer> productIds = requests.stream()
                .map(ProductPurchaseRequest::productId)
                .collect(Collectors.toList());

        List<Product> storedProducts = repository.findAllByIdInOrderById(productIds);

        Map<Integer, Product> productMap = storedProducts.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }

        return requests.stream()
                .map(request -> processPurchaseRequest(request, productMap))
                .collect(Collectors.toList());
    }

    private ProductPurchaseResponse processPurchaseRequest(ProductPurchaseRequest request, Map<Integer, Product> productMap) {
        Product product = productMap.get(request.productId());
        if (product.getAvailableQuantity() < request.quantity()) {
            throw new ProductPurchaseException("Insufficient stock quantity for product with ID: " + request.productId());
        }

        product.setAvailableQuantity(product.getAvailableQuantity() - request.quantity());
        repository.save(product);

        return ProductPurchaseResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(request.quantity())
                .build();
    }
}
