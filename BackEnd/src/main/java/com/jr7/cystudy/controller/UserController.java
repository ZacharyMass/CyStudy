package com.jr7.cystudy.controller;

import com.jr7.cystudy.model.User;
import com.jr7.cystudy.service.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for everything related to User models.
 *
 * @see UserService
 * @see com.jr7.cystudy.repository.UserRepository
 * @see User
 * @author Zach Gorman
 */
@RestController
public class UserController {

  /** @see UserService */
  @Autowired private UserService UserService;

  /** Just a logger that I should use more to debug issues. */
  private final Logger logger = LoggerFactory.getLogger(UserController.class);

  /**
   * Endpoint to get all users in the database.
   *
   * @return List of users, that shows up as a JSON (thanks either Spring or HTTP)
   */
  @GetMapping(path = "/get-users")
  public List<User> getUsers() {
    logger.info("Entered UserController layer in method getUsers().");
    return UserService.getUsers();
  }

  /**
   * Given a role, get all users with a matching role.
   *
   * @param role string of role to get users
   * @return ArrayList of Users with matching roles
   */
  @GetMapping(path = "/get-users-by-role")
  public @ResponseBody List<User> getUsersByRole(@RequestParam String role) {
    logger.info("Entered UserController - getUsersByRole");
    return UserService.getUsersByRole(role);
  }

  /**
   * Gets a User object from the database based on the username specified.
   *
   * @param username name of user object to retrieve. sent as a url parameter
   * @return User object (but actually JSON version)
   */
  @GetMapping(path = "/get-user-by-name")
  public @ResponseBody User getUserByName(@RequestParam String username) {
    logger.info("Entered UserController layer in method getUserByUsername().");
    return UserService.getUserByName(username);
  }

  /**
   * Gets the role of a user specified as a parameter.
   *
   * @param username String of username of a User object to check the role of (sent as url
   *     parameter)
   * @return String of the role of the user, or a string specifying that the user doesn't exist
   */
  @GetMapping(path = "/get-role")
  public String getUserRole(@RequestParam String username) {

    if (UserService.checkUserExists(username)) {

      User u = UserService.getUserByName(username);
      return u.getRole();
    } else {
      return "No user with username " + username + " exists.";
    }
  }

  /**
   * Check if the user exists via request. Likely soon to be deprecated b/c of login.
   *
   * @param username String url parameter of the username portion of a User object you're checking
   *     that exists
   * @return boolean based on if user exists or not
   */
  @GetMapping(path = "/user-exists")
  public @ResponseBody boolean checkUserExists(@RequestParam String username) {
    logger.info("Entered UserController layer in method checkUserExists().");
    return UserService.checkUserExists(username);
  }

  /**
   * Adds a user to the database.
   *
   * @param u User object, with only the username and password
   * @return String saying that either the user exists, or that a user with that username already
   *     exists
   * @throws Exception if username is already taken, don't add this user and throw this exception
   *     instead
   */
  @PostMapping(path = "/add-user", produces = "application/json", consumes = "application/json")
  public @ResponseBody String createUser(@RequestBody User u) throws Exception {

    if (!UserService.checkUserExists(u.getUsername())) {
      UserService.save(u);
      return "Saved user.";
    } else {
      return "User with username "
          + u.getUsername()
          + " already exists. Please choose a new username";
    }
  }

  /**
   * endpoint used for logging in.
   *
   * @param u User object containing ONLY username and password
   * @return String saying that the user doesn't exist, the password was correct, or the password
   *     was incorrect
   * @see UserService
   */
  @PostMapping(path = "/login", produces = "application/json", consumes = "application/json")
  public @ResponseBody String login(@RequestBody User u) {
    if (!UserService.checkUserExists(u.getUsername())) {
      return "No user with that username is registered.";
    }
    return (UserService.login(u))
        ? "correct password for username"
        : "incorrect password for username";
  }
}
