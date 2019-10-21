package com.jr7.cystudy.service;

import com.jr7.cystudy.repository.TermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermsService {

  @Autowired
  private TermsRepository TermsRepository;


}
