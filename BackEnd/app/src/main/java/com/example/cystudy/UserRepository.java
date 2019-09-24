package com.example.cystudy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cystudy.user.*;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
