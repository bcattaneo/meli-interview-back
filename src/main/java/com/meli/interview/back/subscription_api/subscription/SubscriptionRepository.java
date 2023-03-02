package com.meli.interview.back.subscription_api.subscription;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

  //  @Query("SELECT s FROM Subscription s WHERE s.name = ?1")
  Optional<List<Subscription>> findSubscriptionByUserId(String userId);
}
