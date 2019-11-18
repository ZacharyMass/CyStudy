package com.jr7.cystudy.service;

import com.jr7.cystudy.model.UserClass;
import com.jr7.cystudy.repository.UserClassRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Logic behind {@link com.jr7.cystudy.controller.UserClassController}.
 *
 * @author Zach Gorman
 */
@Service
public class UserClassService {

  @Autowired private UserClassRepository UserClassRepository;

  /**
   * Function to get all users and their registered classes.
   *
   * @return List of all users and their classes that they're registered for.
   */
  public List<UserClass> getAllUserClass() {
    return UserClassRepository.findAll();
  }

  /**
   * Logic behind getting a specific user's classes. For more detail, check out the endpoint here:
   * {@link com.jr7.cystudy.controller.UserClassController}.
   *
   * @param username username of the user who's classes you want to get
   * @return string comma separated list of all of the classes a user is registered in.
   */
  public String getUsersClasses(String username) {

    StringBuilder classes = new StringBuilder();
    List<UserClass> uc = UserClassRepository.getUsersClasses(username);
    for (int i = 0; i < uc.size(); i++) {
      classes.append(uc.get(i).getClassName());
      if (i + 1 < uc.size()) classes.append(", ");
    }
    return classes.toString();
  }

  /**
   * Checking if a user is already registered for a class or not. Returns boolean based on result.
   *
   * @param username username to register in a class.
   * @param className the class to register the user in.
   * @return true/false boolean based on if the user is in the class or not.
   */
  public boolean checkUserInClass(String username, String className) {
    List<UserClass> list = UserClassRepository.getUsersClasses(username);
    for (UserClass uc : list) {
      if (uc.getClassName().equalsIgnoreCase(className)) return true;
    }
    return false;
  }

  /**
   * Registering a user to a class by saving to userclass table in the database.
   *
   * @param uc UserClass object containing the username to register for a class.
   */
  public void save(UserClass uc) {
    UserClassRepository.save(uc);
  }
}
