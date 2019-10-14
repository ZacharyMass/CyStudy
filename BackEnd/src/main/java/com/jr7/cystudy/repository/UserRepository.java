package com.jr7.cystudy.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jr7.cystudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String>{

    User   getUserByUsername(String username);
    User   getUserRoleByName(String username);
}
