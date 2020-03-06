package com.example.datawriter;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataWriterApplication.class)
public class DataWriterTest {

    @MockBean
    private JmsTemplate jmsTemplate;

    @MockBean
    ActiveMQConnectionFactory connectionFactory;


    private DataWriter dataWriter;

    @Before
    public void init() {
        dataWriter = new DataWriter(jmsTemplate);
    }

    @Test
    public void shouldProcess() {
        dataWriter.writeCurrentDate();
    }
}