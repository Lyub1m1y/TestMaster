package com.testmaster.service.impl;

import com.testmaster.model.Test;
import com.testmaster.service.TestLoader;
import com.testmaster.service.TestService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Setter;

@Setter
public class TestServiceImpl implements TestService {

  private List<Test> availableTests;
  private Test currentTest;
  private Map<Integer, Integer> answers;

  public TestServiceImpl(TestLoader testLoader) {
    this.availableTests = testLoader.loadTestsFromResources();
    this.answers = new HashMap<>();
  }

  @Override
  public List<Test> getAvailableTests() {
    return availableTests;
  }

  @Override
  public void startTest(Test test) {
  //TODO
  }

  @Override
  public void submitAnswer(int questionIndex, int answerIndex) {

  }

  @Override
  public int getScore() {
    return 0;
  }

  @Override
  public Test getTestByName(String testName) {
    return null; //TODO
  }
}
