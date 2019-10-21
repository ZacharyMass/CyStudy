package com.jr7.cystudy.controller;

import java.util.List;

import com.jr7.cystudy.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jr7.cystudy.model.Classes;
import com.jr7.cystudy.service.*;

@RestController
public class ClassesController {

    @Autowired
    private  ClassesService  ClassesService;

    private final Logger logger = LoggerFactory.getLogger(ClassesController.class);

    @RequestMapping(method = RequestMethod.GET, path="/get-classes")
    public List<Classes> getClasses(){
        logger.info("Entered UserController layer in method getUsers().");
        return ClassesService.getClasses();
    }

    @PostMapping(path="/add-class")
    public @ResponseBody String createClasses(@RequestParam String className) {

        if(ClassesService.checkClassExists(className)) return "Class already exists";

        else{
            Classes c = new Classes();
            c.setClassName(className);
            ClassesService.save(c);

            return "Added new class.";
        }
    }

    @PostMapping(path="/class-exists", produces="application/json", consumes="application/json")
    public @ResponseBody boolean getClassByName(@RequestBody Classes classes){
        logger.info("Entered ClassesController layer in method getClassByName().");
        return ClassesService.checkClassExists(classes.getClassName());
    }
}

