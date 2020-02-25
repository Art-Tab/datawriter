package com.example.javaConsoleApp.config;

import com.example.javaConsoleApp.messaging.MessagingOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;


@Configuration
public class MongodbMessageHandler {
    @Bean
    public com.example.javaConsoleApp.MongodbMessageHandler messageHandler(MessagingOperations messaging,
                                                                           MongoOperations mongo) {
        com.example.javaConsoleApp.MongodbMessageHandler messageHandler = new com.example.javaConsoleApp.MongodbMessageHandler(messaging, mongo);
        messaging.subscribe("test", messageHandler);
        return messageHandler;
    }
}
