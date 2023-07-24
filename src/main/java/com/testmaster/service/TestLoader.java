package com.testmaster.service;

import com.testmaster.model.Test;
import java.util.List;

public interface TestLoader {

  void setDirectoryTests(String directory);

  List<Test> loadTestsFromResources();
}
