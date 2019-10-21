package com.jr7.cystudy.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jr7.cystudy.model.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
@Transactional
public interface TermsRepository extends JpaRepository<Terms, String>{

  List<Terms>  getTermsByClassName(String className);
}
