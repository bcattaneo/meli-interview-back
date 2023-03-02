package com.meli.interview.back.subscription_api.exception;

public class UserNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 8959479918185637340L;

  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(Throwable cause) {
    super(cause);
  }
}
