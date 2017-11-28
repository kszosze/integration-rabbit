package com.example.integrationqueues.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue myDurableQueue() {
        return new Queue("foo", true, false, false);
    }

}
