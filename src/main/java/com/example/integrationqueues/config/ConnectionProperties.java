package com.example.integrationqueues.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
public class ConnectionProperties {

    private String addresses;
    private String username = "foo";
    private String password;
    private String virtualHost = "/";
}
