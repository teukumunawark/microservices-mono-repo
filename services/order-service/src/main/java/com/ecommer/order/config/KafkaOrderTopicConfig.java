package com.ecommer.order.config;

import com.ecommer.order.common.Constant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaOrderTopicConfig {
    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder
                .name(Constant.ORDER_TOPIC)
                .build();
    }
}
