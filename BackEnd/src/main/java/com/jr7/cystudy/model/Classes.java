package com.jr7.cystudy.model;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Classes.getClassByName", query = "SELECT c FROM Classes c WHERE c.className = ?1")
@Table(name="classes")
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="class_id")
    private int classId;

    @Column(name="name")
    private String className;

    public String getClassName(){
        return this.className;
    }

    public void setClassName(String className){
        this.className = className;
    }
}
