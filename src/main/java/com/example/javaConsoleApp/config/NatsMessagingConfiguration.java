package com.example.javaConsoleApp.config;

import com.example.javaConsoleApp.messaging.MessagingOperations;
import com.example.javaConsoleApp.messaging.NatsMessagingTemplate;
import com.example.javaConsoleApp.messaging.factory.NatsConnectionFactory;
import com.example.javaConsoleApp.messaging.factory.SimpleNatsConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NatsMessagingConfiguration {
  @Bean
  public MessagingOperations messagingOperations(NatsConnectionFactory connectionFactory) {
    return new NatsMessagingTemplate(connectionFactory);
  }

  @Bean
  public NatsConnectionFactory natsConnectionFactory(Properties properties) {
    return new SimpleNatsConnectionFactory(properties.getServers().toArray(new String[properties.getServers().size()]));
  }
}
