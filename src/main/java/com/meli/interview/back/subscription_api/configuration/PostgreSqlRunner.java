package com.meli.interview.back.subscription_api.configuration;

import com.meli.interview.back.subscription_api.subscription.Subscription;
import com.meli.interview.back.subscription_api.subscription.SubscriptionRepository;
import com.meli.interview.back.subscription_api.user.User;
import com.meli.interview.back.subscription_api.user.UserRepository;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Profile("dev")
public class PostgreSqlRunner implements CommandLineRunner {

  private SubscriptionRepository subscriptionRepository;
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    // Add subscriptions
    var subscriptions =
        Set.of(
            new Subscription("disney", 100F),
            new Subscription("netflix", 200F),
            new Subscription("spotify", 50F));
    subscriptionRepository.saveAll(subscriptions);

    // Add users
    var users =
        Set.of(
            new User("bcattaneo", "1234", "Bruno Cattáneo"),
            new User("fperez", "1234", "Fernanda Pérez"));
    userRepository.saveAll(users);
  }
}
