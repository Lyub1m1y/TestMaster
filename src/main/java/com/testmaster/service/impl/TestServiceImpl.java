package com.testmaster.service.impl;

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

}
