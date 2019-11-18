package com.jr7.cystudy.service;

import com.jr7.cystudy.model.Terms;
import com.jr7.cystudy.repository.TermsRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Logic behind {@link com.jr7.cystudy.controller.TermsController}.
 *
 * @author Zach Gorman
 */
@Service
public class TermsService {

  /** @see TermsRepository */
  @Autowired private TermsRepository TermsRepository;

  /**
   * Saves a Terms object in the database.
   *
   * @param t a Term object to save in the database
   */
  public void save(Terms t) {
    TermsRepository.save(t);
  }

  /**
   * Gets all of the terms registered in the specified class, and returns them as a list.
   *
   * @param className String of the name of the class to get the terms for
   * @return List of {@link Terms} objects
   */
  public List<Terms> getTermsByClass(String className) {
    return TermsRepository.getTermsByClassName(className);
  }

  /**
   * Function to check that a topic in a class exists.
   *
   * @param className String of the class that the topic is/isn't a part of
   * @param topic String of the name of the topic we're checking exists or not
   * @return boolean based on if topic already exists in the class or not
   */
  public boolean topicExists(String className, String topic) {
    ArrayList<String> topics = getTopics(className);
    return topics.contains(topic);
  }

  /**
   * Gets all topics in a specific class, and returns them as an ArrayList of Strings.
   *
   * @param className String of the name of the class that you want the topics for
   * @return ArrayList of Strings of all topics registered for the class.
   */
  public ArrayList<String> getTopics(String className) {

    List<Terms> termsInClass = getTermsByClass(className);
    ArrayList<String> topics = new ArrayList<>();

    for (Terms t : termsInClass) {
      if (topics.contains(t.getTopic())) continue;
      topics.add(t.getTopic());
    }
    return topics;
  }

  /**
   * Function to remove a card from the database.
   *
   * @param term term portion of the Term object to remove
   * @param answer answer portion of the Term object to remove
   * @param className className portion of the Term object to remove
   * @return String saying "If card existed, or multiple of card existed, it/they are now deleted."
   */
  public String deleteCard(String term, String answer, String className) {
    TermsRepository.deleteCard(term, answer, className);
    return "If card existed, or multiple of card existed, it/they are now deleted.";
  }
}
