package com.jr7.cystudy.controller;

import java.util.List;

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

    private final Logger logger = LoggerFactory.getLogger(ClassesController.class);

    @RequestMapping(method = RequestMethod.GET, path="/get-users-classes")
    public String getUsersClasses(@RequestParam String username){
        logger.info("Entered UserClassController layer in method getUsersClasses().");
        return UserClassService.getUsersClasses(username);
    }

//    @PostMapping(path="/add-class")
//    public @ResponseBody String createClasses(@RequestParam String className) {
//        Classes c = new Classes();
//        c.setClassName(className);
//        ClassesService.save(c);
//
//        return "Added new class.";
//    }
}

