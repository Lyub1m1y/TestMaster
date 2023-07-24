package com.testmaster.service;

import com.testmaster.model.Test;
import java.util.List;

public interface TestService {

  void selectDirectoryTests(String option);

  List<Test> getAvailableTests();

  Test getTestByName(String testName);

  void startTest(Test test);

  void submitAnswer(int questionIndex, int answerIndex);

  int getScore();
}
