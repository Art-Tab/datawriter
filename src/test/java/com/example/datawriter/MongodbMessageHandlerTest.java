package com.example.datawriter;

import com.example.datawriter.mongodb.model.Data;
import com.example.datawriter.mongodb.repository.DataRepository;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataWriterApplication.class)
public class MongodbMessageHandlerTest {
    @Autowired
    private ApplicationArguments args;

    @MockBean
    private MongoOperations mongoOperations;

    @MockBean
    private GridFsTemplate gridFsTemplate;

    @MockBean
    private DataRepository dataRepository;

    @MockBean
    private JmsTemplate jmsTemplate;

    @MockBean
    ActiveMQConnectionFactory connectionFactory;

    private MongodbMessageHandler mongodbMessageHandler;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private String message = null;

    @Before
    public void init(){
        message = dtf.format(LocalDateTime.now());
    }

    @Test
    public void shouldReceiveMessage(){
        mongodbMessageHandler = new MongodbMessageHandler(mongoOperations, dataRepository);
        mongodbMessageHandler.receive(message);
    }


//    @Test
//    public void shouldLogAllDataFromDb(){
//        mongodbMessageHandler.logFindData(dataRepository);
//
//    }

    @Test
    public void shouldRetryAfterDbConnectionException(){
        doThrow(DataAccessException.class)
                .when(mongoOperations).save(any(Data.class));
    }

}