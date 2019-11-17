package com.jr7.cystudy.repository;

import com.jr7.cystudy.model.User;
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
}
