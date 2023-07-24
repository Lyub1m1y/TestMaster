package com.testmaster.service.impl;

import com.testmaster.model.Question;
import com.testmaster.model.Test;
import com.testmaster.service.TestLoader;
import com.testmaster.service.TestService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Setter;

public class TestServiceImpl implements TestService {

  private TestLoader testLoader;
  private static final String RESOURCES_TESTS = "src/main/resources/tests";
  @Setter
  private String directoryTests;
  private List<Test> availableTests;
  private Test currentTest;
  private Map<Integer, Integer> answers;

  public TestServiceImpl(TestLoader testLoader) {
    this.testLoader = testLoader;
    this.answers = new HashMap<>();
  }

  @Override
  public void selectDirectoryTests(String option) {
    if (option.equals("1")) {
      testLoader.setDirectoryTests(RESOURCES_TESTS);
    } else if ((option.equals("2"))) {
      testLoader.setDirectoryTests(directoryTests);
    }
  }

  @Override
  public List<Test> getAvailableTests() {
    this.availableTests = testLoader.loadTestsFromResources();
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
    answers.clear();
  }

  @Override
  public void submitAnswer(int questionIndex, int answerIndex) {
    if (currentTest != null && questionIndex >= 0
        && questionIndex < currentTest.getQuestions().size()) {
      answers.put(questionIndex, answerIndex);
    } else {
      throw new IllegalArgumentException("Invalid question index");
    }
  }

  @Override
  public int getScore() {
    int score = 0;
    if (currentTest != null) {
      List<Question> questions = currentTest.getQuestions();
      for (int i = 0; i < questions.size(); i++) {
        Question question = questions.get(i);
        int correctOptionIndex = question.getCorrectOptionIndex();
        if (answers.containsKey(i) && answers.get(i) == correctOptionIndex) {
          score++;
        }
      }
    }
    return score;
  }

}
