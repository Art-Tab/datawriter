package com.example.javaConsoleApp;

import com.example.javaConsoleApp.messaging.MessagingOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class DataWriter {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    private MessagingOperations messaging;
    String sbuffer = null;

    public DataWriter(MessagingOperations messaging) {
        this.messaging = messaging;
    }

    @Scheduled(fixedDelay = 1000L)
    private void timeout() throws IOException {
        writeCurrentDate();
    }

    private void writeCurrentDate() throws IOException {
        sbuffer = dtf.format(LocalDateTime.now());
        messaging.publish("test",  SerializationUtils.serialize(sbuffer));
        log.info("Message sent to queue: {}", sbuffer);
    }
}
