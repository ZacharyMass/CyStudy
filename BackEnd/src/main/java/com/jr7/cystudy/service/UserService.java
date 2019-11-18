package com.jr7.cystudy.service;

import com.jr7.cystudy.model.User;
import com.jr7.cystudy.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Logic behind {@link com.jr7.cystudy.controller.UserController}.
 *
 * @author Zach Gorman
 */
@Service
public class UserService {

  /** @see UserRepository */
  @Autowired private UserRepository UserRepository;

  @Autowired private final PasswordEncoder passwordEncoder = passwordEncoder();

  /**
   * Used to hash passwords on registration and check that they match at login.
   *
   * @return BCryptPasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Gets all registered users, and returns a list (made into a JSON in the controller).
   *
   * @return List of all users registered
   */
  public List<User> getUsers() {
    return UserRepository.findAll();
  }

  /**
   * Gets a specific user by name. This assumes the user is registered, as it's checked in the
   * controller via calling checkUserExists.
   *
   * @param username String of the username of the User to be returned
   * @return User with matching username
   * @see User
   */
  public User getUserByName(String username) {
    return UserRepository.getUserByUsername(username);
  }

  /**
   * Checking is a user is registered or not.
   *
   * @param username String of User's username that you're checking
   * @return boolean based on if user exists or not
   */
  public boolean checkUserExists(String username) {
    User u = UserRepository.getUserByUsername(username);
    return u != null;
  }

  /**
   * Custom save function to save a user to the database. Custom so that we hash the password before
   * adding the user's information to the database.
   *
   * @param user User model to save. Password is just a string when being passed in
   * @throws Exception If a user with the username already exists, we throw an exception.
   */
  public void save(User user) throws Exception {

    User u = new User();
    if (getUserByName(user.getUsername()) != null) {
      throw new Exception("The username " + user.getUsername() + " is already being used.");
    }

    u.setUsername(user.getUsername());
    u.setPassword(passwordEncoder.encode(user.getPassword()));
    u.setRole(user.getRole());
    UserRepository.save(u);
  }

  /**
   * A login function to ensure that the user logging in has matching credentials to one in the
   * database.
   *
   * @param u User model containing only username and password that is sent from a login request
   * @return boolean depending on if username and password match the user's credentials in the
   *     database
   */
  public boolean login(User u) {

    User realInfo = getUserByName(u.getUsername());
    return passwordEncoder.matches(u.getPassword(), realInfo.getPassword());
  }
}
