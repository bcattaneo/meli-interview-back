package com.meli.interview.back.subscription_api.exception;

public class UserAlreadyExistsException extends RuntimeException {

  private static final long serialVersionUID = 8959479918185637340L;

  public UserAlreadyExistsException() {
    super();
  }

  public UserAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserAlreadyExistsException(String message) {
    super(message);
  }

  public UserAlreadyExistsException(Throwable cause) {
    super(cause);
  }
}
