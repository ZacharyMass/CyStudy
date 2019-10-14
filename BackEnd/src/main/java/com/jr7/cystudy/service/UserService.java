package com.jr7.cystudy.service;

import com.jr7.cystudy.model.User;
import com.jr7.cystudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

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

    public void save(User user) throws Exception{

        if(getUserByName(user.getUsername()) != null){
            throw new Exception("The username " + user.getUsername() + " is already being used.");
        }

        user.setUsername(user.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole());
        UserRepository.save(user);
    }
}
