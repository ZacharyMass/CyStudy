package com.jr7.cystudy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="classes")
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="classId")
    private long classId;

    @Column(name="name")
    private String className;

    public String getClassName(){
        return this.className;
    }

    public void setClassName(String className){
        this.className = className;
    }
}
