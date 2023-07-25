package com.testmaster.service;

import com.testmaster.model.Answer;
import com.testmaster.model.CustomTest;
import com.testmaster.model.Score;
import java.util.List;

public interface TestService {
  List<CustomTest> getAvailableTests();

  CustomTest getTestByName(String testName);

  void testPreparation(CustomTest test);

  void submitAnswer(int questionIndex, Answer answer);

  Score getScore();
}
