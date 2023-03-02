package com.meli.interview.back.subscription_api.user;

import com.meli.interview.back.subscription_api.exception.SubscriptionAlreadyExistsException;
import com.meli.interview.back.subscription_api.exception.UserAlreadyExistsException;
import com.meli.interview.back.subscription_api.exception.UserNotFoundException;
import com.meli.interview.back.subscription_api.exception.UserNotLoggedInException;
import com.meli.interview.back.subscription_api.util.ErrorResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok().body(userService.getUsers());
  }

  @PostMapping
  public void addNewUser(@RequestBody User user) {
    userService.addNewUser(user);
  }

  @DeleteMapping("{userId}")
  public void deleteUser(@PathVariable("userId") Long id) {
    userService.deleteUser(id);
  }

  @PutMapping("{userId}")
  public void updateStudent(@PathVariable("userId") Long id, @RequestBody User user) {
    userService.updateUser(id, user);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(
      UserAlreadyExistsException exception, WebRequest request) {
    var httpStatus = HttpStatus.CONFLICT;
    return new ResponseEntity<>(
        new ErrorResponse(httpStatus.value(), exception.getMessage()), httpStatus);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFoundException(
      UserNotFoundException exception, WebRequest request) {
    var httpStatus = HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(
        new ErrorResponse(httpStatus.value(), exception.getMessage()), httpStatus);
  }

  @ExceptionHandler(UserNotLoggedInException.class)
  public ResponseEntity<ErrorResponse> handleUserNotLoggedInException(
      UserNotLoggedInException exception, WebRequest request) {
    var httpStatus = HttpStatus.BAD_REQUEST;
    return new ResponseEntity<>(
        new ErrorResponse(httpStatus.value(), exception.getMessage()), httpStatus);
  }
}
