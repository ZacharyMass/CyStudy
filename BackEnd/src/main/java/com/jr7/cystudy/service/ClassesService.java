package com.jr7.cystudy.service;

import com.jr7.cystudy.model.Classes;
import com.jr7.cystudy.repository.ClassesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Logic behind {@link com.jr7.cystudy.controller.ClassesController}.
 *
 * @author Zach Gorman
 */
@Service
public class ClassesService {

  /** @see ClassesRepository */
  @Autowired private ClassesRepository ClassesRepository;

  /**
   * Gets all registered classes.
   *
   * @return List of all registered classes
   */
  public List<Classes> getClasses() {
    return ClassesRepository.findAll();
  }

  /**
   * Function to register a class by saving it in the database.
   *
   * @param c Class model
   */
  public void save(Classes c) {
    ClassesRepository.save(c);
  }

  /**
   * Making sure a class by the name specified in the parameter exists in the database.
   *
   * @param className String of the class we're checking exists
   * @return boolean based on if class exists or not
   */
  public boolean checkClassExists(String className) {
    Classes c = ClassesRepository.getClassByClassName(className);
    return c != null;
  }
}
