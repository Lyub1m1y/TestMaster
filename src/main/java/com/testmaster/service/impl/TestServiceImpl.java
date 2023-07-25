package com.testmaster.service.impl;

import com.testmaster.model.Answer;
import com.testmaster.model.Question;
import com.testmaster.model.Score;
import com.testmaster.model.Test;
import com.testmaster.service.TestLoader;
import com.testmaster.service.TestService;

import java.util.ArrayList;
import java.util.List;

public class TestServiceImpl implements TestService {

  private TestLoader testLoader;
  private List<Test> availableTests;
  private Test currentTest;
  private Score score;
  private List<Answer> answers;

  public TestServiceImpl(TestLoader testLoader) {
    this.testLoader = testLoader;
    this.score = new Score();
    this.answers = new ArrayList<>();
  }

  @Override
  public List<Test> getAvailableTests() {
    this.availableTests = testLoader.loadTests();
    return availableTests;
  }

  @Override
  public Test getTestByName(String testName) {
    return availableTests.stream()
        .filter(test -> test.getTestName().equals(testName))
        .findFirst()
        .orElse(null);
  }

  @Override
  public void testPreparation(Test test) {
    currentTest = test;
    score.setNumberOfQuestions(currentTest.getQuestions().size());
    answers.clear();
  }

  @Override
  public void submitAnswer(int questionIndex, Answer answer) {
    if (currentTest != null && questionIndex >= 0
        && questionIndex < currentTest.getQuestions().size()) {
      answers.add(answer);
    } else {
      throw new IllegalArgumentException("Invalid question index");
    }
  }

  @Override
  public Score getScore() {
    if (currentTest != null) {
      List<Question> questions = currentTest.getQuestions();
      for (int i = 0; i < questions.size(); i++) {
        Question question = questions.get(i);
        int correctOptionIndex = question.getCorrectOptionIndex();
        if (answers.get(i).getAnswer() == correctOptionIndex) {
          score.setNumberOfCorrectAnswer(score.getNumberOfCorrectAnswer() + 1);
        }
      }
    }
    return score;
  }

}
