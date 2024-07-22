package com.ecommer.payment.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentNotificationConfiguration {

    @Bean
    public NewTopic paymentNotificationTopic() {
        return TopicBuilder
                .name("payment-topic")
                .build();
    }
}
