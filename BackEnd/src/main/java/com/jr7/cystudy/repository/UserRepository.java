package com.jr7.cystudy.repository;

import com.jr7.cystudy.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Connection to usr table in the MySQL database.
 *
 * @author Zach Gorman
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

  /**
   * Query database to get a User's details based on their username.
   *
   * @see com.jr7.cystudy.service.UserService
   * @param username name of the user to get the information of
   * @return user {@link User}
   */
  User getUserByUsername(String username);

  /**
   * Query database to get all users with matching role.
   *
   * @see com.jr7.cystudy.service.UserService
   * @param role string of role to get all of the users of
   * @return array list of users {@link User}
   */
  List<User> getUsersByRole(String role);
}
