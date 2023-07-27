package com.testmaster.service;

import com.testmaster.model.Answer;
import com.testmaster.model.Score;
import com.testmaster.model.UserTest;
import java.util.List;

public interface TestService {
  List<String> getAvailableTests();

  UserTest getTestByName(String testName);

  void testPreparation(UserTest test);

  void submitAnswer(int questionIndex, Answer answer);

  Score getScore();
}
