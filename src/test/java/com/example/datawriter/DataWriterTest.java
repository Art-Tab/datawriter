package com.example.datawriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DataWriterTest {

    @MockBean
    private JmsTemplate jmsTemplate;

    private DataWriter dataWriter;

    @Before
    public void init() {
        dataWriter = new DataWriter(jmsTemplate);
    }

    @Test
    public void shouldProcess() {
//        dataWriter.writeCurrentDate();
    }

}