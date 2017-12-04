package com.example.integrationqueues.listeners;

import com.example.integrationqueues.config.QueueProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.stereotype.Component;

@Component
public class QueueListeners {

    private static final Logger log = LoggerFactory.getLogger(QueueListeners.class);

    @Autowired
    private QueueProperties queueProperties;

    @Autowired
    private Queue worksQueue;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setQueues(worksQueue);
        listenerContainer.setConcurrentConsumers(1);
        listenerContainer.setExclusive(false);
        listenerContainer.setDefaultRequeueRejected(false);
        listenerContainer.setErrorHandler(errorHandler());
        return listenerContainer;
    }

    @Bean
    public ErrorHandler errorHandler() {
        return new ConditionalRejectingErrorHandler(new ExceptionStrategy());
    }



    @Bean
    public IntegrationFlow amqpInbound(ConnectionFactory connectionFactory) {
        return IntegrationFlows.from(Amqp.inboundAdapter(connectionFactory, queueProperties.getName()))
                .transform(Transformers.objectToString())
                .log()
                .handle(m ->
                    log.info(m.getPayload().toString())
                ).get();
    }

}
