package com.example.integrationqueues.errorhandlers;

import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;

public class ExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {

    @Override
    public boolean isFatal(Throwable t) {

        if (t instanceof ListenerExecutionFailedException) {
            ListenerExecutionFailedException lefe = (ListenerExecutionFailedException) t;
            logger.error(
                    "Failed to process inbound message from queue " + lefe.getFailedMessage().getMessageProperties().getConsumerQueue()
                            + "; failed message: " + lefe.getFailedMessage(),
                    t);
        }
        return super.isFatal(t);
    }}