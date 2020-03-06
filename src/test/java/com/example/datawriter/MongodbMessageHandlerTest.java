package com.example.datawriter;

import ch.qos.logback.core.Appender;
import com.example.datawriter.config.MongodbMessageHandlerConfiguration;
import com.example.datawriter.mongodb.model.Data;
import com.example.datawriter.mongodb.repository.DataRepository;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MongodbMessageHandlerConfiguration.class)
public class MongodbMessageHandlerTest {
    @MockBean
    private MongoOperations mongoOperations;

    @MockBean
    private GridFsTemplate gridFsTemplate;

    @MockBean
    private DataRepository dataRepository;

    private MongodbMessageHandler mongodbMessageHandler;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private String message = null;
    private List<Data> data;
    private Logger logger = LoggerFactory.getLogger(MongodbMessageHandler.class);

    @Before
    public void init(){
        mongodbMessageHandler = new MongodbMessageHandler(mongoOperations, dataRepository);
        message = dtf.format(LocalDateTime.now());
        data = generateData();
    }

    @Test
    public void shouldReceiveMessage() {
        mongodbMessageHandler.receive(message);
        verify(mongoOperations, times(1)).save(any(Data.class));
    }


//    @Test
//    public void shouldLogAllDataFromDb(){
//        when(dataRepository.findAll()).thenReturn(data);
//        mongodbMessageHandler.logFindData(dataRepository);
//        verify(logger, times(data.size()+1)).info(anyString());
//    }

    @Test
    public void shouldRetryAfterDbConnectionException(){
        DataAccessException exception = Mockito.mock(DataAccessException.class);
        when(mongoOperations.save(any(Data.class)))
                .thenThrow(exception).thenThrow(exception).thenReturn(any(Data.class));
        mongodbMessageHandler = new MongodbMessageHandler(mongoOperations, dataRepository);
        mongodbMessageHandler.receive(message);
    }

    private List<Data> generateData() {
        Data data;
        List<Data> dataList = new ArrayList<>();
        while (dataList.size()<10){
            data = new Data();
            data.setCurrrentDate(dtf.format(LocalDateTime.now()));
            dataList.add(data);
        }
        return dataList;
    }
}