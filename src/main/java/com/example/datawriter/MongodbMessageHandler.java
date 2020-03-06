package com.example.datawriter;

import com.example.datawriter.mongodb.model.Data;
import com.example.datawriter.mongodb.repository.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.jms.annotation.JmsListener;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MongodbMessageHandler {
    @Autowired
    private ApplicationArguments args;

    private DataRepository dataRepository;
    private MongoOperations mongo;

    public MongodbMessageHandler(MongoOperations mongo, DataRepository dataRepository) {
        this.mongo = mongo;
        this.dataRepository = dataRepository;
    }

    @PostConstruct
    private void init() {
        if (args.getSourceArgs().length > 0 && args.getSourceArgs()[0].equals("-p")) {
            logFindData(dataRepository);
            System.exit(0);
        }
    }

    @JmsListener(destination = "neotech.q")
    public void receive(String message) {
        log.info("Took message from queue: {}", message);
        Data data = new Data();
        data.setCurrrentDate(message);
        saveData(data);
    }

    private void saveData(Data data) {
        try {
            log.info("Save to DB : {}", data.getCurrrentDate());
            mongo.save(data);
        } catch (DataAccessException ex) {
            log.warn("Error connecting to the database");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                log.warn("Failed to set reconnection timeout");
            }
            saveData(data);
        }
    }

    public void logFindData(DataRepository dataRepository){
        List<Data> data = dataRepository.findAll();
        if (data.isEmpty()) {
            log.warn("No entries found");
        } else {
            for (Data buffer : data) {
                log.info(buffer.getCurrrentDate());
            }
        }
        log.info("Result {} entries", data.size());
    }
}
