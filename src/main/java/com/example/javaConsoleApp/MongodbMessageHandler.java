package com.example.javaConsoleApp;

import com.example.javaConsoleApp.messaging.MessageHandler;
import com.example.javaConsoleApp.messaging.MessagingOperations;
import com.example.javaConsoleApp.mongodb.model.Data;
import com.mongodb.MongoSocketOpenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.data.mongodb.core.MongoOperations;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MongodbMessageHandler implements MessageHandler<String> {
    @Autowired
    private ApplicationArguments args;

    private MessagingOperations messaging;
    private MongoOperations mongo;
    private List<Data> buffer = new ArrayList<>();
    private int count = 0;

    Data data = null;

    public MongodbMessageHandler(MessagingOperations messaging,
                                 MongoOperations mongo) {
        this.messaging = messaging;
        this.mongo = mongo;
    }

    @PostConstruct
    private void init() {
        if (args.getSourceArgs().length > 0 && args.getSourceArgs()[0].equals("-p")) {
            buffer = mongo.findAll(Data.class);
            if (buffer.isEmpty()) {
            log.warn("No entries found");
            } else {
                for (Data buffer : buffer) {
                    count++;
                    System.out.println(count + " Вывод:" + buffer.getCurrrentDate());
                }
            }
            System.exit(0);
        }
    }

    @Override
    public void onMessage(String message) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Took message from queue: {}", message);
        data = new Data();
        data.setCurrrentDate(message);
        saveData(data);
    }

    private void saveData(Data data) {
        try {
            log.info("Save to DB : {}", data.getCurrrentDate());
//            mongo.collectionExists(Data.class);
            mongo.save(data);
        } catch (Exception ex) {
            log.warn("Error connecting to the database");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                log.warn("Failed to set reconnection timeout");
            }
            saveData(data);
        }
    }
}
