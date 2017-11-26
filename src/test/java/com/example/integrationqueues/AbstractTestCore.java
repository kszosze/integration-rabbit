package com.example.integrationqueues;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ContextConfiguration(initializers = AbstractTestCore.Initializer.class)
public abstract class AbstractTestCore {

  /*  @ClassRule
    public static GenericContainer rabbit =
            new GenericContainer("rabbitmq:3.6.14")
                    .withExposedPorts(4369, 5671, 5672, 25672)
                    .withEnv("RABBITMQ_USER", "guest")
                    .withEnv("RABBITMQ_PASSWORD", "guest");

   public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            EnvironmentTestUtils.addEnvironment("testcontainers", configurableApplicationContext.getEnvironment(),
                    "spring.rabbitmq.addresses=" + rabbit.getContainerIpAddress()+":"+rabbit.getMappedPort(5671));
        }
    }*/
}
