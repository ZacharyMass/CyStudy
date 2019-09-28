package com.jr7.cystudy.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jr7.cystudy.model.User;
import com.jr7.cystudy.service.*;

@RestController
public class UserController {

    @Autowired
    private  UserService  UserService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET, path="/getusers")
    public List<User> getUsers(){
        logger.info("Entered UserController layer in method getUsers().");
        return UserService.getUsers();
    }
}

