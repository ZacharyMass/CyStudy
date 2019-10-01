package com.jr7.cystudy.model;

import javax.persistence.*;

@Entity
@NamedQuery(name = "UserClass.getUsersClasses",
            query =
            "SELECT uc FROM UserClass uc WHERE uc.username = ?1")
@Table(name="usr_class")
public class UserClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="class_name")
    private String className;

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getClassName(){
        return this.className;
    }

    public void setClassName(String className){
        this.className = className;
    }
}
