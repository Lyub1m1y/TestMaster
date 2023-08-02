package com.testmaster.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestResult {

  private User user;
  private UserTest userTest;
  private int numberOfQuestions;
  private int numberOfCorrectAnswer;

  public TestResult(UserTest userTest) {
    this.userTest = userTest;
    numberOfQuestions = userTest.getQuestions().size();
  }
}
