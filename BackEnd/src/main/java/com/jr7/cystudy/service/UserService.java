package com.jr7.cystudy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr7.cystudy.model.User;
import com.jr7.cystudy.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    public List<User> getUsers() {
        return UserRepository.findAll();
    }

}
