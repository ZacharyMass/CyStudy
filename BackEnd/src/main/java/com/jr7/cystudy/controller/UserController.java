package com.jr7.cystudy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jr7.cystudy.model.User;
import com.jr7.cystudy.service.*;

@RestController
public class UserController {

    @Autowired
    private  UserService  UserService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(path="/get-users")
    public List<User> getUsers(){
        logger.info("Entered UserController layer in method getUsers().");
        return UserService.getUsers();
    }

    @GetMapping(path="/get-user-by-name")
    public @ResponseBody User getUserByName(@RequestParam String username){
        logger.info("Entered UserController layer in method getUserByUsername().");
        return UserService.getUserByName(username);
    }

    @GetMapping(path="/user-exists")
    public @ResponseBody String checkUserExists(@RequestParam String username){
        logger.info("Entered UserController layer in method checkUserExists().");
        return UserService.checkUserExists(username);
    }

    @PostMapping(path="/add-user")
    public @ResponseBody String createUser(@RequestParam String username,
                                           @RequestParam String pass,
                                           @RequestParam String role) {
        User n = new User();
        n.setUsername(username);
        n.setPassword(pass);
        n.setRole(role);
        UserService.save(n);

        return "Saved user.";
    }
}

