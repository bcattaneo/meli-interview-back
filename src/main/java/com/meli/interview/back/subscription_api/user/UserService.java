package com.meli.interview.back.subscription_api.user;

import com.meli.interview.back.subscription_api.exception.SubscriptionAlreadyExistsException;
import com.meli.interview.back.subscription_api.exception.SubscriptionNotFoundException;
import com.meli.interview.back.subscription_api.exception.UserAlreadyExistsException;
import com.meli.interview.back.subscription_api.exception.UserNotFoundException;
import com.meli.interview.back.subscription_api.exception.UserNotLoggedInException;
import com.meli.interview.back.subscription_api.user.User;
import com.meli.interview.back.subscription_api.subscription.Subscription;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserService {

  private UserRepository userRepository;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public void addNewUser(User user) {
    var maybeUser = userRepository.findByUsername(user.getUsername());
    if (maybeUser.isPresent()) {
      throw new UserAlreadyExistsException("User already exists");
    }
    userRepository.save(user);
  }

  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new UserNotFoundException("User not found");
    }
    userRepository.deleteById(id);
  }

  @Transactional
  public void updateUser(Long id, User userUpdate) {
    var maybeUser = userRepository.findById(id);
    if (!maybeUser.isPresent()) {
      // Note: we aren't creating one here because we're using sequential IDs
      throw new UserNotFoundException("User not found");
    } else {
      // Update existing user
      var user = maybeUser.get();
      user.setName(userUpdate.getName());
      System.out.println(userUpdate.getSubscriptions());
      user.setSubscriptions(userUpdate.getSubscriptions());
    }
  }
}
