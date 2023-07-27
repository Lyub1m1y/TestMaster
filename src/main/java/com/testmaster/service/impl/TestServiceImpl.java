package com.testmaster.service.impl;

import com.testmaster.model.Answer;
import com.testmaster.model.Score;
import com.testmaster.model.UserTest;
import com.testmaster.repository.TestRepository;
import com.testmaster.service.TestService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

  @NonNull
  private final TestRepository repository;

  @Override
  public List<String> getAvailableTests() {
    return repository.getTestsNames();
  }

  @Override
  public UserTest getTestByName(String testName) {
    return repository.getTestByName(testName);
  }

  @Override
  public void testPreparation(UserTest test) {
    // TODO
  }

  @Override
  public void submitAnswer(int questionIndex, Answer answer) {
    // TODO
  }

  @Override
  public Score getScore() {
    return null;
  }

}
