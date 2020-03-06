package com.example.datawriter;

import com.example.datawriter.mongodb.repository.DataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataWriterApplication.class)
public class MongodbMessageHandlerTest {
    @MockBean
    private MongoOperations mongoOperations;

    @MockBean
    private GridFsTemplate gridFsTemplate;

    @MockBean
    private DataRepository dataRepository;

    @Test
    public void shouldLogAllDatafromDb(){
        System.out.println("sds");
//        args.getSourceArgs()[0] =
//        mongodbMessageHandler = new MongodbMessageHandler(mongoOperations, dataRepository);
    }

}