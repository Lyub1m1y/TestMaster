package com.testmaster.service;

import com.testmaster.model.UserTest;
import java.util.List;

public interface TestService {
  List<String> getAvailableTests();

  UserTest getTestByName(String testName);

}
