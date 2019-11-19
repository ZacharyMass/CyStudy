package com.jr7.cystudy.service;

import com.jr7.cystudy.model.Game;
import com.jr7.cystudy.model.Terms;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  @Autowired private TermsService termsService;

  /**
   * Gets the questions based on the classname by querying the database.
   *
   * @param g the current game
   * @param className the name of the class to get the terms for
   * @see TermsService
   */
  public List<Terms> getQuestions(Game g, String className) {

    g.questions = termsService.getTermsByClass(className);
    return g.questions;
  }
}
