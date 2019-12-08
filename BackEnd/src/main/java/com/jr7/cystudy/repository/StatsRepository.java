package com.jr7.cystudy.repository;

import com.jr7.cystudy.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Connection to stats table in the MySQL database.
 *
 * @author Zach Gorman
 */
@Repository
@Transactional
public interface StatsRepository extends JpaRepository<Stats, String> {

  Stats getStatsByUsernameAndClassName(String username, String className);
}
