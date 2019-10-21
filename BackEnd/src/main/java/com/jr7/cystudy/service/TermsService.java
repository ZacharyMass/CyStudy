package com.jr7.cystudy.service;

import com.jr7.cystudy.model.Terms;
import com.jr7.cystudy.repository.TermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TermsService {

  @Autowired
  private TermsRepository TermsRepository;

  public void save(Terms t){
    TermsRepository.save(t);
  }

  public List<Terms> getTermsByClass(String className){
    return TermsRepository.getTermsByClassName(className);
  }

  public boolean topicExists(String className, String topic){
    ArrayList<String> topics = getTopics(className);
    return topics.contains(topic);
  }

  public ArrayList<String> getTopics(String className){

    List<Terms> termsInClass = getTermsByClass(className);
    ArrayList<String> topics = new ArrayList<>();

    for(Terms t : termsInClass){
      if(topics.contains(t.getTopic())) continue;
      topics.add(t.getTopic());
    }
    return topics;
  }

  public String deleteCard(String term, String answer, String className){
    TermsRepository.deleteCard(term, answer, className);
    return "If card existed, or multiple of card existed, it/they are now deleted.";
  }
}
