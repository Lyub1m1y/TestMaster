package com.testmaster.service.impl;

import com.testmaster.model.Question;
import com.testmaster.model.Test;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCsvLoaderImplTest {

  private TestCsvLoaderImpl testCsvLoader;

  @BeforeEach
  void setUp() {
    testCsvLoader = new TestCsvLoaderImpl();
  }

  @org.junit.jupiter.api.Test
  @DisplayName("Should return tests")
  void loadTestsFromResources_shouldReturnTests() {
    testCsvLoader.setDirectoryTests("src/test/resources/tests");
    Test expectedTest = new Test("Math", List.of(new Question("2+2?", List.of("5","22","4"),3)));

    List<Test> tests = testCsvLoader.loadTestsFromResources();

    assertEquals(expectedTest,tests.get(0));
  }

  @org.junit.jupiter.api.Test
  @DisplayName("Should throw exception when invalid directory")
  void loadTestsFromResources_shouldReturnEmptyListOnInvalidDirectory() {
    testCsvLoader.setDirectoryTests("FailDirectory");

    List<Test> tests = testCsvLoader.loadTestsFromResources();

    assertTrue(tests.isEmpty());
  }
}