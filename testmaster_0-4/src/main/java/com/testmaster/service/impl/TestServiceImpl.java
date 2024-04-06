package com.testmaster.service.impl;

import com.testmaster.model.UserTest;
import com.testmaster.repository.UserTestRepository;
import com.testmaster.service.TestService;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

  private final UserTestRepository repository;

  @Override
  public List<String> getAvailableTests() {
    return repository.getTestNames();
  }

  @Override
  public UserTest getTestByName(String testName) {
    return repository.getTestByName(testName);
  }
}
