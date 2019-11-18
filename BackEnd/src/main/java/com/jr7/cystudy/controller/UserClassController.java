package com.jr7.cystudy.controller;

import com.jr7.cystudy.model.UserClass;
import com.jr7.cystudy.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * UserClassController is exactly what it's name says. This connects to the userclass table in the
 * database to store all of the classes the a user is connected to. This uses both the User and
 * Classes models.
 *
 * @see com.jr7.cystudy.model.Classes
 * @see com.jr7.cystudy.model.User
 * @author Zach Gorman
 */
@RestController
public class UserClassController {

  @Autowired private UserClassService UserClassService;

  @Autowired private UserService UserService;

  @Autowired private ClassesService ClassesService;

  private final Logger logger = LoggerFactory.getLogger(ClassesController.class);

  /**
   * The endpoint used to GET all of the classes a specific user is registered in.
   *
   * @param username a string of a specific user's name. This comes from the url, not a json.
   * @return classes returns a comma separated string of all classes a user is in
   */
  @GetMapping(path = "/get-users-classes")
  public String getUsersClasses(@RequestParam String username) {
    logger.info("Entered UserClassController layer in method getUsersClasses().");
    return UserClassService.getUsersClasses(username);
  }

  /**
   * Endpoint to register a user for a specific class. This first checks to ensure that a user and
   * class exist, and then checks to see if the user is already registered in the class. If the user
   * and class exist, and the user is not already registered for the specified class, then the user
   * is registered for the class.
   *
   * @param uC JSON cast to a userclass object containing at least a username and classname.
   * @return string either "User is not registered," "Class does not exist," "User already in
   *     class," or "user added to class."
   */
  @PostMapping(path = "/add-to-class")
  public @ResponseBody String addUserToClass(@RequestBody UserClass uC) {
    if (!UserService.checkUserExists(uC.getUsername())) {
      return "User is not registered";
    }
    if (!ClassesService.checkClassExists(uC.getClassName())) {
      return "Class does not exist";
    }
    if (UserClassService.checkUserInClass(uC.getUsername(), uC.getClassName())) {
      return "User " + uC.getUsername() + " is already in " + uC.getClassName();
    }
    UserClassService.save(uC);
    return uC.getUsername() + " added to " + uC.getClassName();
  }
}
