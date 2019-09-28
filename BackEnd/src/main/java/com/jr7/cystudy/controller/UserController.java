package com.jr7.cystudy.controller;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jr7.cystudy.repository.UserRepository;
import com.jr7.cystudy.model.User;
import com.jr7.cystudy.service.*;

@RestController
public class UserController {

    @Autowired
    private  UserService  UserService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET, path="/get-users")
    public List<User> getUsers(){
        logger.info("Entered UserController layer in method getUsers().");
        return UserService.getUsers();
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

