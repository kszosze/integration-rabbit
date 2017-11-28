package com.example.integrationqueues.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private static final Logger log = LoggerFactory.getLogger(RabbitConfig.class);

    @Autowired
    private ConnectionProperties connectionProperties;

    @Bean
    public ConnectionFactory defaultConnectionFactory() {
        CachingConnectionFactory cf = new CachingConnectionFactory();
        cf.setAddresses(connectionProperties.getAddresses());
        cf.setUsername(connectionProperties.getUsername());
        cf.setPassword(connectionProperties.getPassword());
        cf.setVirtualHost(connectionProperties.getVirtualHost());
        System.out.println("Connection Rabbit Factory addr: " +
                        connectionProperties.getAddresses() +
                " Username: " + connectionProperties.getUsername() +
                " Password: " + connectionProperties.getPassword() +
                " VirtualHost: " + connectionProperties.getVirtualHost());
        return cf;
    }

}
