package com.ecommer.order.service;

import com.ecommer.order.error.exception.BusinessException;
import com.ecommer.order.model.entity.CustomerOrder;
import com.ecommer.order.model.request.CustomerOrderLineRequest;
import com.ecommer.order.model.request.CustomerOrderRequest;
import com.ecommer.order.model.request.PaymentRequest;
import com.ecommer.order.model.request.PurchaseRequest;
import com.ecommer.order.model.response.OrderConfirmation;
import com.ecommer.order.repository.CustomerOrderRepository;
import com.ecommer.order.service.integration.CustomerClient;
import com.ecommer.order.service.integration.PaymentClient;
import com.ecommer.order.service.integration.ProductClient;
import com.ecommer.order.service.producer.CustomerOrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCustomerOrderService {
    private final CustomerOrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final CustomerOrderLineService customerOrderLineService;
    private final PaymentClient paymentClient;
    private final CustomerOrderProducer producer;

    @Transactional
    public Integer execute(CustomerOrderRequest request) {
        var customer = customerClient.getCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        var purchasedProducts = productClient.purchaseProducts(request.products());

        var order = orderRepository.save(CustomerOrder.builder()
                .reference(request.reference())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .build());

        for (PurchaseRequest purchaseRequest : request.products()) {
            customerOrderLineService.saveCustomerOrderLine(
                    CustomerOrderLineRequest.builder()
                            .orderId(order.getId())
                            .productId(purchaseRequest.productId())
                            .quantity(purchaseRequest.quantity())
                            .build()
            );
        }

        paymentClient.requestOrderPayment(PaymentRequest.builder()
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(order.getId())
                .orderReference(order.getReference())
                .customer(customer)
                .build());

        producer.sendOrderConfirmation(
                OrderConfirmation.builder()
                        .orderReference(request.reference())
                        .totalAmount(request.amount())
                        .paymentMethod(request.paymentMethod())
                        .customer(customer)
                        .products(purchasedProducts)
                        .build()
        );

        return order.getId();
    }
}
