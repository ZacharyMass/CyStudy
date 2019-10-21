package com.jr7.cystudy.model;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Terms.getTermsByClassName", query = "SELECT t FROM Terms t WHERE t.className = ?1")
@NamedQuery(name = "Terms.deleteCard", query = "DELETE FROM Terms t WHERE t.term = ?1 AND t.answer = ?2 AND t.className = ?3")
@Table(name="terms")
public class Terms {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private long id;

  @Column(name="class_name")
  private String className;

  @Column(name="topic")
  private String topic;

  @Column(name="term")
  private String term;

  @Column(name="answer")
  private String answer;

  @Column(name="time")
  private double timeSpent;

  Terms(){}

  Terms(String className, String topic, String term, String answer){
    this.className = className;
    this.topic = topic;
    this.term = term;
    this.answer = answer;
  }

  public String getClassName(){
    return this.className;
  }

  public String getTopic(){
    return this.topic;
  }

  public String getTerm(){
    return this.term;
  }

  public String getAnswer(){
    return this.answer;
  }

  public double getTimeSpent(){
    return this.timeSpent;
  }
}
