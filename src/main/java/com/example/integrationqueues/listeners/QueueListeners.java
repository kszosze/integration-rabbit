package com.example.integrationqueues.listeners;

import com.example.integrationqueues.config.QueueProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.stereotype.Component;

@Component
public class QueueListeners {

    private static final Logger log = LoggerFactory.getLogger(QueueListeners.class);

    @Autowired
    private QueueProperties queueProperties;

    @Bean
    public IntegrationFlow amqpInbound(ConnectionFactory connectionFactory) {
        return IntegrationFlows.from(Amqp.inboundAdapter(connectionFactory, queueProperties.getName()))
                .handle(m -> log.info(m.getPayload().toString())).get();
    }

}
