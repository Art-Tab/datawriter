package com.example.datawriter;

import com.example.datawriter.mongodb.model.Data;
import com.example.datawriter.mongodb.repository.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.jms.annotation.JmsListener;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MongodbMessageHandler {
    @Autowired
    private ApplicationArguments args;

    DataRepository dataRepository;
    private MongoOperations mongo;
  //  private List<Data> buffer = new ArrayList<>();
    private int count = 0;

    Data data = null;

    public MongodbMessageHandler(MongoOperations mongo, DataRepository dataRepository ) {
        this.mongo = mongo;
        this.dataRepository= dataRepository;
    }

    @PostConstruct
    private void init() {
        if (args.getSourceArgs().length > 0 && args.getSourceArgs()[0].equals("-p")) {
            List<Data> data = dataRepository.findAll();
          //  data = mongo.findAll(Data.class);
            if (data.isEmpty()) {
                log.warn("No entries found");
            } else {
                for (Data buffer : data) {
                    count++;
                    System.out.println(count + " Вывод:" + buffer.getCurrrentDate());
                }
            }
            System.exit(0);
        }
    }


    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @JmsListener(destination = "neotech.q")
    public void receive(String message) {
        log.info("Took message from queue: {}", message);
        data = new Data();
        data.setCurrrentDate(message);
        saveData(data);
        latch.countDown();
    }

    private void saveData(Data data) {
        try {
            log.info("Save to DB : {}", data.getCurrrentDate());
            mongo.save(data);
        } catch (DataAccessResourceFailureException ex) {
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
