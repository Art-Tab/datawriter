package com.example.javaConsoleApp.messaging;

import com.example.javaConsoleApp.exception.MessageProcessingException;
import com.example.javaConsoleApp.exception.ProcessingException;
import com.example.javaConsoleApp.messaging.factory.NatsConnectionFactory;
import io.nats.client.Connection;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;


public class NatsMessagingTemplate implements MessagingOperations {
  private NatsConnectionFactory connectionFactory;

  public NatsMessagingTemplate(NatsConnectionFactory connectionFactory){
    this.connectionFactory = connectionFactory;
  }

  @Override
  public Connection getConnection() throws IOException {
    return connectionFactory.getConnection();
  }

  @Override
  public void publish(String subject, byte[] data) throws IOException {
    getConnection().publish(subject, data);
  }

  @Override
  public <T extends Serializable> void subscribe(String subject, MessageHandler<T> handler) {
    try {
      getConnection().createDispatcher
              (message -> processMessage(message, handler)).subscribe(subject);
    } catch (IOException ex) {
      throw new MessageProcessingException("Ошибка при получении сообщения из очереди с темой {0}", ex,
        subject);
    }
  }

  private <T extends Serializable> void processMessage(io.nats.client.Message message, MessageHandler<T> handler) {
    T object = null;
    try {
      object = (T) deserializeMessage(message.getData());
      handler.onMessage(object);
    } catch (Exception ex) {
      if (ex instanceof ProcessingException) {
//        logger.error(((ProcessingException) ex).getMarker(), "Ошибка обработки запроса по теме {}: {}",
//                message.getSubject(), ex.getMessage(), ex);
      } else {
//        logger.error("Ошибка обработки запроса по теме {}: {}", message.getSubject(), ex.getMessage(), ex);
      }
    }
  }

  private <T extends Serializable> byte[] serializeMessage(T message) {
    byte [] data = SerializationUtils.serialize(message);
    return data;
  }

  private Object deserializeMessage(byte[] data) throws IOException {
    Object resultObject = SerializationUtils.deserialize(data);
    return resultObject;
  }
}
