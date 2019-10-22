package com.jr7.cystudy.service;

import com.jr7.cystudy.model.User;
import com.jr7.cystudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder = passwordEncoder();

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public List<User> getUsers() {
        return UserRepository.findAll();
    }

    public User getUserByName(String username){
        return UserRepository.getUserByUsername(username);
    }

    public boolean checkUserExists(String username){
        User u = UserRepository.getUserByUsername(username);
        return u != null;
    }

    public void save(User user) throws Exception{

        User u = new User();
        if(getUserByName(user.getUsername()) != null){
            throw new Exception("The username " + user.getUsername() + " is already being used.");
        }

        u.setUsername(user.getUsername());
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        u.setRole(user.getRole());
        UserRepository.save(u);
    }

    public boolean login(User u){

        User realInfo = getUserByName(u.getUsername());
        return passwordEncoder.matches(u.getPassword(), realInfo.getPassword());
    }
}
