package com.example.javaConsoleApp.config;

import com.example.javaConsoleApp.MongodbMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;


@Configuration
public class MongodbMessageHandlerConfiguration {
    @Bean
    public MongodbMessageHandler receiver(MongoOperations mongoOperations) {
        return new MongodbMessageHandler(mongoOperations);
    }
}
