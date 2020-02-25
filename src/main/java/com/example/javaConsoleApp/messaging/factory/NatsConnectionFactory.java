package com.example.javaConsoleApp.messaging.factory;

import io.nats.client.Connection;

import java.io.IOException;

public interface NatsConnectionFactory {
  Connection getConnection() throws IOException;
}
