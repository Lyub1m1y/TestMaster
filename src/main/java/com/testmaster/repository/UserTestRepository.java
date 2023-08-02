package com.testmaster.repository;

import com.testmaster.model.UserTest;
import java.util.List;

public interface UserTestRepository {

  List<String> getTestsNames();

  UserTest getTestByName(String testName);
}
