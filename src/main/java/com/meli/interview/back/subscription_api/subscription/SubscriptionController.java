package com.meli.interview.back.subscription_api.subscription;

import com.meli.interview.back.subscription_api.exception.SubscriptionAlreadyExistsException;
import com.meli.interview.back.subscription_api.exception.SubscriptionNotFoundException;
import com.meli.interview.back.subscription_api.util.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.context.request.WebRequest;

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

  @ExceptionHandler(SubscriptionAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleSubscriptionAlreadyExistsException(
      SubscriptionAlreadyExistsException exception, WebRequest request) {
    var httpStatus = HttpStatus.CONFLICT;
    return new ResponseEntity<>(
        new ErrorResponse(httpStatus.value(), exception.getMessage()), httpStatus);
  }

  @ExceptionHandler(SubscriptionNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleSubscriptionNotFoundException(
      SubscriptionNotFoundException exception, WebRequest request) {
    var httpStatus = HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(
        new ErrorResponse(httpStatus.value(), exception.getMessage()), httpStatus);
  }
}
