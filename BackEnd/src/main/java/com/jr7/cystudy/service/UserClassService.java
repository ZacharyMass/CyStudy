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
        for(int i = 0; i < uc.size(); i++){
            classes.append(uc.get(i).getClassName());
            if(i+1 < uc.size()) classes.append(", ");
        }
        return classes.toString();
    }

    public boolean checkUserInClass(String username, String className){
        List<UserClass> list = UserClassRepository.getUsersClasses(username);
        for(UserClass uc : list){
            if(uc.getClassName().equalsIgnoreCase(className)) return true;
        }
        return false;
    }

    public void save(UserClass uc) {
        UserClassRepository.save(uc);
    }
}
