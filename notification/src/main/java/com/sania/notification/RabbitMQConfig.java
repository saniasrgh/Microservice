package com.sania.notification;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE = "order.notification.queue";

    public RabbitMQConfig(){

    }

    @Bean
    public Queue queue(){
        return new Queue("order.notification.queue");
    }
}
