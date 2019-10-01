package com.jr7.cystudy.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jr7.cystudy.model.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
@Transactional
public interface UserClassRepository extends JpaRepository<UserClass, String>{

    List<UserClass> getUsersClasses(String username);
}
