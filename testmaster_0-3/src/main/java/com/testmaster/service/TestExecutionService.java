package com.testmaster.service;

import com.testmaster.model.TestResult;
import com.testmaster.model.UserTest;

public interface TestExecutionService {

  TestResult executeTest(UserTest selectedTest);

}
