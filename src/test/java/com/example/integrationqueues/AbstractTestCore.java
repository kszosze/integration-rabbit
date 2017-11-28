package com.example.integrationqueues;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.GenericContainer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = IntegrationQueuesApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = AbstractTestCore.Initializer.class, classes = TestConfig.class)
public abstract class AbstractTestCore {

    private static final Logger log = LoggerFactory.getLogger(AbstractTestCore.class);

    @ClassRule
    public static GenericContainer rabbit =
            new GenericContainer("rabbitmq:3.6.14")
                    .withExposedPorts(4369, 5671, 5672, 25672)
                    .withEnv("RABBITMQ_USER", "guest")
                    .withEnv("RABBITMQ_PASSWORD", "guest");

    @Before
    public void setUp() {
        rabbit.start();
    }

   public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            log.debug("Rabbit Machine IP {}, Rabbit Port {}", rabbit.getContainerIpAddress(), rabbit.getMappedPort(5671));
            EnvironmentTestUtils.addEnvironment("testcontainers", configurableApplicationContext.getEnvironment(),
                    "spring.rabbitmq.addresses=" + rabbit.getContainerIpAddress() + ":" + rabbit.getMappedPort(5671));
        }
    }
}
