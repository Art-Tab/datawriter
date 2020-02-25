package com.example.javaConsoleApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class DataWriter {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private JmsTemplate jmsTemplate;
    String sbuffer = null;

    public DataWriter(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedDelay = 1000L)
    private void timeout() throws IOException {
        writeCurrentDate();
    }

    private void writeCurrentDate() throws IOException {
        sbuffer = dtf.format(LocalDateTime.now());
        jmsTemplate.convertAndSend("neotech.q", sbuffer);
        log.info("Message sent to queue: {}", sbuffer);
    }
}
