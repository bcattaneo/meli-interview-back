package com.meli.interview.back.subscription_api.subscription;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subscription")
@AllArgsConstructor
public class SubscriptionController {

  private final SubscriptionService subscriptionService;

  @GetMapping
  public ResponseEntity<List<Subscription>> getAllSubscriptions() {
    return ResponseEntity.ok().body(subscriptionService.getSubscriptions());
  }

  @PostMapping
  public void addNewSubscription(@RequestBody Subscription subscription) {
    subscriptionService.addNewSubscription(subscription);
  }

  @DeleteMapping("{subscriptionId}")
  public void deleteSubscription(@PathVariable("subscriptionId") Long id) {
    subscriptionService.deleteSubscription(id);
  }

  @PutMapping("{subscriptionId}")
  public void updateStudent(
      @PathVariable("subscriptionId") Long id, @RequestBody Subscription subscription) {
    subscriptionService.updateSubscription(id, subscription);
  }
}
