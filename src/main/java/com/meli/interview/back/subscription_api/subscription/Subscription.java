package com.meli.interview.back.subscription_api.subscription;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

  @NonNull
  @Column(unique = true)
  private String partner;

  @NonNull private Float price;

  //  public float getPrice() {
  //    float price = 0;
  //    if (partner.equals("disney")) {
  //      price = 100;
  //    }
  //
  //    if (partner.equals("netflix")) {
  //      price = 200;
  //    }
  //
  //    if (partner.equals("spotify")) {
  //      price = 50;
  //    } else {
  //      price = 0;
  //    }
  //
  //    return price;
  //  }
}
