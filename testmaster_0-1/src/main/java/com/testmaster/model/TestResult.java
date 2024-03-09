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

  public void submitAnswer(Question currentQuestion, int answer, TestResult testResult) {
    answer--;
    if (currentQuestion != null && answer >= 0
        && answer < currentQuestion.getOptions().size()) {
      if (checkAnswer(currentQuestion, answer)) {
        testResult.setNumberOfCorrectAnswer(testResult.getNumberOfCorrectAnswer() + 1);
      }
    }
  }

  private boolean checkAnswer(Question currentQuestion, int answer) {
    boolean correct = false;
    if (currentQuestion.getOptions().get(answer).isCorrect()) {
      correct =  true;
    }
    return correct;
  }
}
