package com.jr7.cystudy.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jr7.cystudy.model.User;
// import com.jr7.cystudy.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Transactional
public interface CyStudyRepository extends JpaRepository<User, String>{
}
