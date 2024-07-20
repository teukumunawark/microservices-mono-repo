package com.ecommer.email.model.entity;

import com.ecommer.email.model.dto.OrderConfirmation;
import com.ecommer.email.model.dto.PaymentConfirmation;
import com.ecommer.email.model.enumerated.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
