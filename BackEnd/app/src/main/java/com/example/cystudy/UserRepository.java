package com.example.cystudy;

import org.springframework.data.repository.CrudRepository;

import com.example.cystudy.user.*;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
public interface UserRepository extends CrudRepository<User, Integer> {

}
