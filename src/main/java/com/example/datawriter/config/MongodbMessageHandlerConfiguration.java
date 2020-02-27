package com.example.datawriter.config;

import com.example.datawriter.MongodbMessageHandler;
import com.example.datawriter.mongodb.repository.DataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;


@Configuration
public class MongodbMessageHandlerConfiguration {
    @Bean
    public MongodbMessageHandler receiver(MongoOperations mongoOperations, DataRepository dataRepository) {
        return new MongodbMessageHandler(mongoOperations, dataRepository);
    }
}
