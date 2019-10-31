package com.jr7.cystudy.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jr7.cystudy.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Connection to classes table in the MySQL database.
 * @author Zach Gorman
 */
@Repository
@Transactional
public interface ClassesRepository extends JpaRepository<Classes, String>{

  /**
   * @param  name     name of the class
   * @return Classes  object with all of the information of the class based on the name parameter
   * @see com.jr7.cystudy.service.ClassesService
   * @see Classes
   */
  Classes getClassByClassName(String name);
}
