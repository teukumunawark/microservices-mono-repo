package com.ecommer.email.consumer;

import com.ecommer.email.model.dto.PaymentConfirmation;
import com.ecommer.email.model.entity.Notification;
import com.ecommer.email.model.enumerated.NotificationType;
import com.ecommer.email.repository.EmailRepository;
import com.ecommer.email.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationsConsumer {

    private final EmailService emailService;
    private final EmailRepository repository;

    @KafkaListener(topics = "payment-notification-topic")
    public void consumePaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Consuming the message from payment-topic Topic:: %s", paymentConfirmation);
        repository.save(Notification.builder()
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build()
        );
        var customerName = StringUtils.concat(paymentConfirmation.customerFirstname(), " ", paymentConfirmation.customerLastname());
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }
}
