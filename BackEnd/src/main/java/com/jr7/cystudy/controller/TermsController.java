package com.jr7.cystudy.controller;

import com.jr7.cystudy.model.Terms;
import com.jr7.cystudy.service.ClassesService;
import com.jr7.cystudy.service.TermsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for all things having to do with Terms objects
 *
 * @see Terms
 * @see TermsService
 * @see com.jr7.cystudy.repository.TermsRepository
 * @see ClassesService
 * @author Zach Gorman
 */
@RestController
public class TermsController {

  /**
   * @see TermsService
   */
  @Autowired
  private TermsService TermsService;

  /**
   * @see ClassesService
   */
  @Autowired
  private ClassesService ClassesService;

  /**
   * Logger for debugging
   */
  private final Logger log = LoggerFactory.getLogger(TermsController.class);

  /**
   * Endpoint to add a term to a class. Checks if the class exists, and if it does, adds the card. We don't check if the
   * topic exists, b/c it either didn't, or does now with this addition. That will be fixed... later...
   *
   * @param t Terms object containing at least term, answer, and classname
   * @return String saying that the class doesn't exist, or was succesfully added.
   */
  @PostMapping(path="/add-card")
  public @ResponseBody String addFlashCard(@RequestBody Terms t){

    log.info("Entered addFlashCard() in TermsController.");

    if(!ClassesService.checkClassExists(t.getClassName())){
      log.info("Class name for flashcard in addFlashCard() does not exist");
      return "Class " + t.getClassName() + " does not exist.";
    } else{

      TermsService.save(t);
      log.info("Saved flashcard in addFlashCard() in TermsController");
      return "Flashcard succesfully added.";
    }
  }

  /**
   * Endpoint to get all of the terms belonging to a specific class
   * @param className name of the class to get the terms for
   * @return List of Terms objects (JSON when called from endpoint).
   * @see TermsService
   */
  @GetMapping(path="/get-terms-by-class")
  public @ResponseBody List<Terms> getTermsByClass(@RequestParam String className){
    return TermsService.getTermsByClass(className);
  }

  /**
   * Endpoint to get all of the terms for a specific topic in a specific class. Class name must be specified b/c it is
   * possible for multiple classes to have topics of the same name (e.g. Chapter 1, etc.).
   *
   * @param className name of the class the topic belongs to
   * @param topic name of the topic
   * @return null if the topic doesn't exist, or the List of Terms objects (as a JSON if called via HTTP)
   */
  @GetMapping(path="get-terms-by-topic")
  public @ResponseBody List<Terms> getTermsByTopic(@RequestParam String className, @RequestParam String topic){

    if(!TermsService.topicExists(className, topic)){
      return null;
    }

    List<Terms> allTermsForClass = TermsService.getTermsByClass(className);
    List<Terms> termsByTopic = new ArrayList<>();

    for(Terms t : allTermsForClass){
      if(t.getTopic().equalsIgnoreCase(topic)){
        termsByTopic.add(t);
      }
    }

    return termsByTopic;
  }

  /**
   * Endpoint to get all of the topics for a specific class.
   *
   * @param className name of the class to ghet the topics of
   * @return ArrayList of Strings containing all of the topics
   */
  @GetMapping(path="get-class-topics")
  public @ResponseBody ArrayList<String> getClassTopics(@RequestParam String className){
    return TermsService.getTopics(className);
  }

  /**
   * Endpoint to delete a specific flashcard that matches the specified parameters
   *
   * @param t Terms object containing at least classname, term, and answer
   * @return String defined in {@link TermsService}
   */
  @PostMapping(path="/delete-card", produces="application/json", consumes="application/json")
  public @ResponseBody String deleteCard(@RequestBody Terms t){
    return TermsService.deleteCard(t.getTerm(), t.getAnswer(), t.getClassName());
  }
}
