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

@RestController
public class TermsController {

  @Autowired
  private TermsService TermsService;

  @Autowired
  private ClassesService ClassesService;

  private final Logger log = LoggerFactory.getLogger(TermsController.class);

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

  @GetMapping(path="/get-terms-by-class")
  public @ResponseBody List<Terms> getTermsByClass(@RequestParam String className){
    return TermsService.getTermsByClass(className);
  }

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

  @GetMapping(path="get-class-topics")
  public @ResponseBody ArrayList<String> getClassTopics(@RequestParam String className){
    return TermsService.getTopics(className);
  }
}
