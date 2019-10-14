package com.jr7.cystudy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jr7.cystudy.model.User;
import com.jr7.cystudy.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private UserDetailsService userDetailsService;

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

    public User getUserRoleByName(String username){
        return UserRepository.getUserRoleByName(username);
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

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
