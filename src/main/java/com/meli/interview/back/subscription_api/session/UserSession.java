package com.meli.interview.back.subscription_api.session;

import com.meli.interview.back.subscription_api.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserSession {
  private String token;
  private User user;
}
