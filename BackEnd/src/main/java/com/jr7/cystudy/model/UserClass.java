package com.jr7.cystudy.model;

import javax.persistence.*;

/**
 * UserClass model used to register a user in a class. This is the model used in {@link
 * com.jr7.cystudy.controller.UserClassController}, {@link
 * com.jr7.cystudy.service.UserClassService}, and {@link
 * com.jr7.cystudy.repository.UserClassRepository}.
 *
 * @author Zach Gorman
 */
@Entity
@NamedQuery(
    name = "UserClass.getUsersClasses",
    query = "SELECT uc FROM UserClass uc WHERE uc.username = ?1")
@Table(name = "usr_class")
public class UserClass {

  /** Autogenerated id, which is the key to each object in the table. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  /** Username of the user registered to a class. */
  @Column(name = "username")
  private String username;

  /** Class a user is registered in. */
  @Column(name = "class_name")
  private String className;

  /**
   * Getter method to get the username of the user registered.
   *
   * @return username
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Getter method to get the name of the class a user is registered for.
   *
   * @return classname
   */
  public String getClassName() {
    return this.className;
  }
}
