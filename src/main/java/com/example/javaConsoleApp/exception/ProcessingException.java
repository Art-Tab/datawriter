package com.example.javaConsoleApp.exception;

import java.text.MessageFormat;

public class ProcessingException extends RuntimeException {
  private static final long serialVersionUID = 7516491577973416811L;

  public ProcessingException(String format, Object... args) {
    super(new MessageFormat(format).format(args));
  }

  public ProcessingException(String format, Throwable cause, Object... args) {
    super(new MessageFormat(format).format(args), cause);
  }


}
