package com.meli.interview.back.subscription_api.subscription;

import com.meli.interview.back.subscription_api.exception.SubscriptionAlreadyExistsException;
import com.meli.interview.back.subscription_api.exception.SubscriptionNotFoundException;
import com.meli.interview.back.subscription_api.exception.UserNotLoggedInException;
import com.meli.interview.back.subscription_api.user.User;
import com.meli.interview.back.subscription_api.session.UserSession;

import com.meli.interview.back.subscription_api.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SubscriptionService {

  private SubscriptionRepository subscriptionRepository;
  private UserRepository userRepository;
  private UserSession userSession;

  public List<Subscription> getSubscriptions() {
    return subscriptionRepository.findAll();
  }

  public void addNewSubscription(Subscription subscription) {
    var maybeSubscription =
        subscriptionRepository.findSubscriptionByPartner(subscription.getPartner());
    if (maybeSubscription.isPresent()) {
      throw new SubscriptionAlreadyExistsException("Subscription already exists");
    }
    subscriptionRepository.save(subscription);
  }

  public void deleteSubscription(Long id) {
    if (!subscriptionRepository.existsById(id)) {
      throw new SubscriptionNotFoundException("Subscription not found");
    }
    subscriptionRepository.deleteById(id);
  }

  @Transactional
  public void updateSubscription(Long id, Subscription subscriptionUpdate) {
    var maybeSubscription = subscriptionRepository.findById(id);
    if (!maybeSubscription.isPresent()) {
      // Note: we aren't creating one here because we're using sequential IDs
      throw new SubscriptionNotFoundException("Subscription not found");
    } else {
      // Update existing subscription
      var subscription = maybeSubscription.get();
      subscription.setPartner(subscriptionUpdate.getPartner());
      subscription.setPrice(subscriptionUpdate.getPrice());
    }
  }

  /**
   * Devuelve el costo total de las suscripciones de un usuario siempre que el usuario que est??
   * logueado se encuentre en su lista de amigos
   *
   * @param username
   * @return costo total de la suscripciones del user
   * @throws UserNotLoggedInException si no hay un usuario logueado
   */
  public Float getUserSubscriptionsCost(String username) throws UserNotLoggedInException {
    var loggedUser = userSession.getUser();

    // When it's just me, return my cost sum
    if (loggedUser.getUsername().equals(username)) {
      return loggedUser.getSubscriptions().stream()
          .map(s -> s.getPrice())
          .reduce(0F, (x, y) -> x + y);
    }

    // Get target user
    var maybeUser = userRepository.findByUsername(username);
    if (!maybeUser.isPresent()) {
      throw new SubscriptionNotFoundException("Target user not found");
    }

    var user = maybeUser.get();

    // TODO: define "equals" in User's model and just use List.contains?
    boolean isFriend =
        user.getFriends().stream().anyMatch(friend -> friend.getId().equals(loggedUser.getId()));

    if (isFriend) {
      return user.getSubscriptions().stream()
          .map(s -> s.getPrice())
          .reduce(0F, (x, y) -> x + y)
          .floatValue();
    }

    return 0F;
  }
}
