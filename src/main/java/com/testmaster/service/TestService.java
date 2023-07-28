package com.testmaster.service;

import com.testmaster.model.Question;
import com.testmaster.model.TestResult;
import com.testmaster.model.UserTest;
import java.util.List;

public interface TestService {
  List<String> getAvailableTests();

  UserTest getTestByName(String testName);

  void submitAnswer(Question currentQuestion, int answer, TestResult testResult);
}
