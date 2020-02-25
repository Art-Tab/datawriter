package com.example.javaConsoleApp.exception;

public class MessageProcessingException extends ProcessingException {
  private static final long serialVersionUID = 7760116171072437434L;

  public MessageProcessingException(String format, Object... args) {
    super(format, args);
  }

  public MessageProcessingException(String format, Throwable cause, Object... args) {
    super(format, cause, args);
  }
}
