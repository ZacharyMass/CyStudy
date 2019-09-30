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

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getUsers() {
        return UserRepository.findAll();
    }

    public User getUserByName(String username){
        return UserRepository.getUserByUsername(username);
    }

    public String checkUserExists(String username){
        User u = UserRepository.getUserByUsername(username);
        if(u == null){
            return "False";
        } else{
            return "True";
        }
    }

    public void save(User user) {
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setRole(user.getRole());
        UserRepository.save(user);
    }
}
