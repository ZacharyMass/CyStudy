package com.jr7.cystudy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr7.cystudy.model.Classes;
import com.jr7.cystudy.repository.ClassesRepository;

@Service
public class ClassesService {

    @Autowired
    private ClassesRepository ClassesRepository;

    public List<Classes> getClasses() {
        return ClassesRepository.findAll();
    }

    public void save(Classes c) {
        c.setClassName(c.getClassName());
        ClassesRepository.save(c);
    }
}
