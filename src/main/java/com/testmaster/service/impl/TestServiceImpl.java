package com.testmaster.service.impl;

import com.testmaster.model.Question;
import com.testmaster.model.TestResult;
import com.testmaster.model.UserTest;
import com.testmaster.repository.UserTestRepository;
import com.testmaster.service.TestService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

  @NonNull
  private final UserTestRepository repository;

  @Override
  public List<String> getAvailableTests() {
    return repository.getTestsNames();
  }

  @Override
  public UserTest getTestByName(String testName) {
    return repository.getTestByName(testName);
  }

  @Override
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
