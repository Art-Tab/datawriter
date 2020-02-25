package com.example.javaConsoleApp.messaging.factory;

import io.nats.client.Connection;
import io.nats.client.ConnectionListener;
import io.nats.client.Nats;
import io.nats.client.Options;
import io.nats.streaming.NatsStreaming;
import io.nats.streaming.StreamingConnection;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SimpleNatsConnectionFactory implements NatsConnectionFactory {

    private Options options;
    private Connection connection;

    public SimpleNatsConnectionFactory(String[] servers) {
        ConnectionListener connectionListener = (conn, type) -> {
            log.info("Change NATS Connection Status: {}", type.toString());
        };
        this.options = new Options.Builder().servers(servers).maxReconnects(-1).
                connectionListener(connectionListener).build();
    }

    @Override
    public Connection getConnection() throws IOException {
        if (connection == null) {
            try {
                connection =
                        Nats.connect(options);
            } catch (InterruptedException ex) {
                throw new IOException("Error creating connection to NATS: ", ex);
            }
        }
        return connection;
    }
}
