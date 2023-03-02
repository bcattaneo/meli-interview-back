package com.meli.interview.back.subscription_api.session;

import com.meli.interview.back.subscription_api.exception.CollaboratorCallException;
import java.util.Optional;

public class UserSession {

  private static final UserSession userSession = new UserSession();

  private UserSession() {
  }

  public static UserSession getInstance() {
    return userSession;
  }

  public Optional<User> getLoggedUser() {
    throw new CollaboratorCallException(
        "UserSession.getLoggedUser() should not be called in an unit test");
  }

}
