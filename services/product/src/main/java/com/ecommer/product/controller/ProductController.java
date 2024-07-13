package com.ecommer.product.controller;

import com.ecommer.product.model.request.CreateProductRequest;
import com.ecommer.product.model.request.ProductPurchaseRequest;
import com.ecommer.product.model.response.CreateProductResponse;
import com.ecommer.product.model.response.GetProductResponse;
import com.ecommer.product.model.response.ProductPurchaseResponse;
import com.ecommer.product.service.CreateProductPurchaseService;
import com.ecommer.product.service.CreateProductService;
import com.ecommer.product.service.GetProductByIdService;
import com.ecommer.product.service.GetProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final CreateProductService createProductService;
    private final GetProductService getProductService;
    private final GetProductByIdService getProductByIdService;
    private final CreateProductPurchaseService createProductPurchaseService;

    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(
            @RequestBody @Valid CreateProductRequest request
    ) {
        var product = createProductService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public ResponseEntity<List<GetProductResponse>> getProducts() {
        return ResponseEntity.ok(getProductService.execute());
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<GetProductResponse> getProductById(
            @PathVariable("product-id") Integer productId
    ) {
        return ResponseEntity.ok(getProductByIdService.execute(productId));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody @Valid List<ProductPurchaseRequest> request
    ) {
        return ResponseEntity.ok(createProductPurchaseService.execute(request));
    }
}
