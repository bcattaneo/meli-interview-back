package com.meli.interview.back.subscription_api.exception;

public class UserInvalidCredentialsException extends RuntimeException {

  private static final long serialVersionUID = 8959479918185637340L;

  public UserInvalidCredentialsException() {
    super();
  }

  public UserInvalidCredentialsException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserInvalidCredentialsException(String message) {
    super(message);
  }

  public UserInvalidCredentialsException(Throwable cause) {
    super(cause);
  }
}
