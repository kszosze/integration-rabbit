package com.example.integrationqueues;

import org.junit.Test;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;

public class QueueListenersTest extends AbstractTestCore {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private Exchange theExchange;

    @Test
    public void queueListenerTest() {
        amqpTemplate.send(theExchange.getName(), "#", new Message("Send a Message".getBytes(), MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setMessageId("123")
                .setHeader("bar", "baz")
                .build()));
    }
}
