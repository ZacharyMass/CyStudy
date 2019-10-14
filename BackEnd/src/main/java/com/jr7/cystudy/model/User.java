package com.jr7.cystudy.model;

import javax.persistence.*;

@Entity
@NamedQuery(name = "User.getUserByUsername", query = "SELECT u FROM User u WHERE u.username = ?1")
@NamedQuery(name = "User.getUserRoleByName", query = "SELECT u FROM User u WHERE u.username = ?1")
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String pass){
        this.password = pass;
    }

    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }

}
