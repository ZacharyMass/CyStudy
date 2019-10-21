package com.jr7.cystudy.controller;

import com.jr7.cystudy.service.TermsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TermsController {

  @Autowired
  private TermsService TermsService;

  private final Logger log = LoggerFactory.getLogger(TermsController.class);


}
