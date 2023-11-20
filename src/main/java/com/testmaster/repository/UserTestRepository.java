package com.testmaster.repository;

import com.testmaster.model.UserTest;
import java.util.List;

public interface UserTestRepository {

  List<String> getTestNames();

  UserTest getTestByName(String testName);
}
