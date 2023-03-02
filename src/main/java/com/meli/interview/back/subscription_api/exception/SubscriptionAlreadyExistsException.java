package com.meli.interview.back.subscription_api.exception;

public class SubscriptionAlreadyExistsException extends RuntimeException {

  private static final long serialVersionUID = 8959479918185637340L;

  public SubscriptionAlreadyExistsException() {
    super();
  }

  public SubscriptionAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  public SubscriptionAlreadyExistsException(String message) {
    super(message);
  }

  public SubscriptionAlreadyExistsException(Throwable cause) {
    super(cause);
  }
}
