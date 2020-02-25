package com.example.javaConsoleApp.messaging;

import java.io.Serializable;
import java.util.List;

public interface MessageHandler<T extends Serializable> {
  void onMessage(T message);
}
