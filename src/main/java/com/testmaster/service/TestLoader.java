package com.testmaster.service;

import com.testmaster.model.Test;
import java.util.List;

public interface TestLoader {

  List<Test> loadTestsFromResources();
}
