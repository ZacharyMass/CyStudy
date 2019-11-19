package com.jr7.cystudy.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

  public List<Terms> questions;

  public int round;

  public String player1;
  public String player2;

  public int p1Correct;
  public int p2Correct;

  /** Default constructor. */
  public Game() {
    questions = new ArrayList<>();
    p1Correct = 0;
    p2Correct = 0;
  }
}
