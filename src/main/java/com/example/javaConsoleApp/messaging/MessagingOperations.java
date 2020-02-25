package com.example.javaConsoleApp.messaging;

import io.nats.client.Connection;

import java.io.IOException;
import java.io.Serializable;

public interface MessagingOperations {
  Connection getConnection() throws IOException;

  void publish(String subject, byte[] data) throws IOException;
  <T extends Serializable> void subscribe(String subject, MessageHandler<T> handler);
}
