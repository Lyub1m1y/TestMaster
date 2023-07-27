package com.testmaster.repository;

import com.testmaster.model.UserTest;
import java.util.List;

public interface TestRepository {

  List<String> getTestsNames();

  UserTest getTestByName();
}
