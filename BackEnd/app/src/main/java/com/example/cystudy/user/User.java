package com.example.cystudy.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="users")
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(name="first_name") private String first_name;
  @Column(name="last_name") private String last_name;
  @Column(name="role") private String role;
  @Column(name="password") private String password;

  public Integer getId() {
    return id;
	}

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return first_name;
  }

  public void setFirstName(String first_name) {
    this.first_name = first_name;
  }

  public String getLastName() {
    return last_name;
  }

  public void setLastName(String last_name) {
    this.last_name = last_name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
