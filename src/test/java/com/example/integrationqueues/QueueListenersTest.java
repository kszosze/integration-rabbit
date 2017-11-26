package com.example.integrationqueues;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class QueueListenersTest extends AbstractTestCore {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void queueListenerTest() {
        amqpTemplate.convertAndSend("Send a Message");
    }
}
