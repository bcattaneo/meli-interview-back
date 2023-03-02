package com.meli.interview.back.subscription_api.exception;

public class SubscriptionNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 8959479918185637340L;

  public SubscriptionNotFoundException() {
    super();
  }

  public SubscriptionNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public SubscriptionNotFoundException(String message) {
    super(message);
  }

  public SubscriptionNotFoundException(Throwable cause) {
    super(cause);
  }
}
