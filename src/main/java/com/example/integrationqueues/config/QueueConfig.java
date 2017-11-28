package com.example.integrationqueues.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    private Queue worksQueue() {
        return new Queue("foo", true, false, false);
    }
    @Bean
    private Exchange worksExchange() {
        return ExchangeBuilder.topicExchange("foo.exchange")
                .build();
    }
    @Bean
    Binding worksBinding() {
        return BindingBuilder
                .bind(worksQueue())
                .to(worksExchange()).with("#").noargs();
    }

}
