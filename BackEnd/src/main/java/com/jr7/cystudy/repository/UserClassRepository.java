package com.jr7.cystudy.repository;

import com.jr7.cystudy.model.UserClass;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Connection to usr_class table in the MySQL database.
 *
 * @author Zach Gorman
 */
@Repository
@Transactional
public interface UserClassRepository extends JpaRepository<UserClass, String> {

  /**
   * Query the database for all of the classes that a user is in.
   *
   * @param username name of the user to get the registered classes of
   * @return List of classes a user is registered in
   * @see com.jr7.cystudy.service.UserClassService
   */
  List<UserClass> getUsersClasses(String username);
}
