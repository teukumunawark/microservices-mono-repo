package com.ecommer.payment.service;

import com.ecommer.payment.model.entity.Payment;
import com.ecommer.payment.model.request.PaymentNotificationRequest;
import com.ecommer.payment.model.request.PaymentRequest;
import com.ecommer.payment.repository.PaymentRepository;
import com.ecommer.payment.service.producer.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final NotificationProducer notificationProducer;


    public Integer execute(PaymentRequest request) {
        var payment = repository.save(
                Payment.builder()
                        .amount(request.amount())
                        .paymentMethod(request.paymentMethod())
                        .orderId(request.orderId())
                        .build()
        );


        notificationProducer.sendNotification(
                PaymentNotificationRequest.builder()
                        .orderReference(request.orderReference())
                        .amount(request.amount())
                        .paymentMethod(request.paymentMethod())
                        .customerFirstname(request.customer().firstname())
                        .customerLastname(request.customer().lastname())
                        .customerEmail(request.customer().email())
                        .build()
        );

        return payment.getId();
    }
}
