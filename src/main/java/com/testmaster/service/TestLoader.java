package com.testmaster.service;

import com.testmaster.model.CustomTest;
import java.util.List;

public interface TestLoader {
  List<CustomTest> loadTests();
}
