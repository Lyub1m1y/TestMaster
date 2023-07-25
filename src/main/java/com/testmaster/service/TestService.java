package com.testmaster.service;

import com.testmaster.model.Answer;
import com.testmaster.model.Score;
import com.testmaster.model.Test;
import java.util.List;

public interface TestService {
  List<Test> getAvailableTests();

  Test getTestByName(String testName);

  void testPreparation(Test test);

  void submitAnswer(int questionIndex, Answer answer);

  Score getScore();
}
