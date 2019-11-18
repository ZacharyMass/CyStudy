package com.jr7.cystudy.controller;

import com.jr7.cystudy.model.Classes;
import com.jr7.cystudy.service.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for everything related to Classes object
 *
 * @see com.jr7.cystudy.repository.ClassesRepository
 * @see ClassesService
 * @see Classes
 * @author Zach Gorman
 */
@RestController
public class ClassesController {

  /** @see ClassesService */
  @Autowired private ClassesService ClassesService;

  /** Logger for debugging info */
  private final Logger logger = LoggerFactory.getLogger(ClassesController.class);

  /**
   * Function to get all registered classes. Calls function of the same name in ClassesService
   *
   * @return List of Classes objects (returned as JSON when called via API)
   */
  @RequestMapping(method = RequestMethod.GET, path = "/get-classes")
  public List<Classes> getClasses() {
    logger.info("Entered UserController layer in method getUsers().");
    return ClassesService.getClasses();
  }

  /**
   * Endpoint to register a class
   *
   * @param className String sent via URL parameter
   * @return String saying if the class already exists, or if it was added succesfully.
   */
  @PostMapping(path = "/add-class")
  public @ResponseBody String createClasses(@RequestParam String className) {

    if (ClassesService.checkClassExists(className)) return "Class already exists";
    else {
      Classes c = new Classes();
      c.setClassName(className);
      ClassesService.save(c);

      return "Added new class.";
    }
  }

  /**
   * Endpoint to check if a class exists. Not sure when this would be used, but figured it might be
   * useful at some point on some admin page to check if a className exists before it's added in
   *
   * @param classes Classes object with all relevant info (minus id)
   * @return boolean of depending on whether the class exists or not
   */
  @PostMapping(path = "/class-exists", produces = "application/json", consumes = "application/json")
  public @ResponseBody boolean getClassByName(@RequestBody Classes classes) {
    logger.info("Entered ClassesController layer in method getClassByName().");
    return ClassesService.checkClassExists(classes.getClassName());
  }
}
