package com.example.datawriter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    private void timeout() {
        writeCurrentDate();
    }

    public void writeCurrentDate() {
        sbuffer = dtf.format(LocalDateTime.now());
        jmsTemplate.convertAndSend("neotech.q", sbuffer);
        log.info("Sent message to queue: {}", sbuffer);
    }
}
