package com.example.integrationqueues;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class IntegrationQueuesApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(IntegrationQueuesApplication.class)
				.web(false)
				.run(args);
	}
}
