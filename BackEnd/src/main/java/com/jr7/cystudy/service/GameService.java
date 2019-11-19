package com.jr7.cystudy.service;

import com.jr7.cystudy.model.Game;
import com.jr7.cystudy.model.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

  @Autowired private TermsService termsService;

  public List<Terms> getQuestions(Game g, String className){

    g.questions = termsService.getTermsByClass(className);
    return g.questions;
  }
}
