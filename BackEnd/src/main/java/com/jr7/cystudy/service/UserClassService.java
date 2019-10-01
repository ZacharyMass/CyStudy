package com.jr7.cystudy.service;

import java.util.List;

import com.jr7.cystudy.repository.UserClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr7.cystudy.model.UserClass;

@Service
public class UserClassService {

    @Autowired
    private UserClassRepository UserClassRepository;

    public List<UserClass> getAllUserClass() {
        return UserClassRepository.findAll();
    }

    public String getUsersClasses(String username){

        StringBuilder classes = new StringBuilder();
        List<UserClass> uc = UserClassRepository.getUsersClasses(username);
        for (UserClass userClass : uc) {
            classes.append(userClass.getClassName());
            classes.append(", ");
        }
        return classes.toString();
    }

//    public String checkUserInClass(String username){
//        UserClass u = UserClassRepository.getUsersClasses(username);
//        if(u == null){
//            return "False";
//        } else{
//            return "True";
//        }
//    }

    public void save(UserClass uc) {
        uc.setUsername(uc.getUsername());
        uc.setClassName(uc.getClassName());
        UserClassRepository.save(uc);
    }
}
