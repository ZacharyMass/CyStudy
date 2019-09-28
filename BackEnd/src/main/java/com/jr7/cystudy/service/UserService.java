package com.jr7.cystudy.service;

import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr7.cystudy.model.User;
import com.jr7.cystudy.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers() {
        return UserRepository.findAll();
    }

    public void save(User user) {
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setRole(user.getRole());
        UserRepository.save(user);
    }
}
