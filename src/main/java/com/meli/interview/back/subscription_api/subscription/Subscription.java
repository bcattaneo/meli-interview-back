package com.meli.interview.back.subscription_api.subscription;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meli.interview.back.subscription_api.user.User;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Subscription {

  @Id
  @SequenceGenerator(
      name = "subscription_sequence",
      sequenceName = "subscription_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_sequence")
  private Long id;

  @Column(unique = true, nullable = false)
  @NonNull
  private String partner;

  @Column(nullable = false)
  @NonNull
  private Float price;
}
