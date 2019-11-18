package com.jr7.cystudy.repository;

import com.jr7.cystudy.model.Terms;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Connection to terms table in the MySQL database.
 *
 * @author Zach Gorman
 */
@Repository
@Transactional
public interface TermsRepository extends JpaRepository<Terms, String> {

  /**
   * Get all of the flashcards (AKA terms) that a specific class has.
   *
   * @param className name of the class to get the terms for
   * @return List of all flashcards in a class
   */
  List<Terms> getTermsByClassName(String className);

  /**
   * Modifying command to the terms table to delete a specific term.
   *
   * @param term The "question" part of a flashcard
   * @param answer The "answer" part of a flashcard
   * @param className The name of the class that the flashcard is in
   * @see com.jr7.cystudy.service.TermsService
   */
  @Modifying
  void deleteCard(String term, String answer, String className);
}
