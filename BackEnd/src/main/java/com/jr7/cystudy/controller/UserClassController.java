package com.jr7.cystudy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jr7.cystudy.model.UserClass;
import com.jr7.cystudy.service.*;

@RestController
public class UserClassController {

    @Autowired
    private  UserClassService UserClassService;

    @Autowired
    private UserService UserService;

    @Autowired
    private ClassesService ClassesService;

    private final Logger logger = LoggerFactory.getLogger(ClassesController.class);

    @GetMapping(path="/get-users-classes")
    public String getUsersClasses(@RequestParam String username){
        logger.info("Entered UserClassController layer in method getUsersClasses().");
        return UserClassService.getUsersClasses(username);
    }

    @PostMapping(path="/add-to-class")
    public @ResponseBody String addUserToClass(@RequestBody UserClass uC){
        if(!UserService.checkUserExists(uC.getUsername())){
            return "User is not registered.";
        }
        if(!ClassesService.checkClassExists(uC.getClassName())){
            return "Class does not exist";
        }
        if(UserClassService.checkUserInClass(uC.getUsername(), uC.getClassName())){
            return "User " + uC.getUsername() + " is already in " + uC.getClassName();
        }
        UserClassService.save(uC);
        return uC.getUsername() +" added to "+uC.getClassName();
    }
}

