package com.testmaster.service.impl;

import com.testmaster.model.Question;
import com.testmaster.model.Test;
import com.testmaster.service.TestLoader;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestServiceImplTest {

  private TestServiceImpl testService;
  private Test expectedTest;

  @BeforeEach
  void setUp() {
    TestLoader testLoader = new TestCsvLoaderImpl();
    testLoader.setDirectoryTests("src/test/resources/tests");
    testService = new TestServiceImpl(testLoader);
    expectedTest = new Test("Math", List.of(new Question("2+2?", List.of("5","22","4"),3)));
  }

  @org.junit.jupiter.api.Test
  @DisplayName("Should return available tests")
  void getAvailableTests_shouldReturnAvailableTests() {
    List<Test> availableTests = testService.getAvailableTests();

    assertTrue(availableTests.size() > 0);
    assertTrue(availableTests.stream().anyMatch(test -> test.getTestName().equals("Math")));
  }

  @org.junit.jupiter.api.Test
  @DisplayName("Should return test by name")
  void getTestByName_shouldReturnTestByName() {
    testService.getAvailableTests();
    Test actualTest = testService.getTestByName("Math");

    assertEquals(expectedTest, actualTest);
  }


  @org.junit.jupiter.api.Test
  @DisplayName("Should return correct score")
  void getScore_shouldReturnCorrectScore() {
    testService.testPreparation(expectedTest);
    testService.submitAnswer(0, 3);
    int expectedScore = 1;

    int actualScore = testService.getScore();

    assertEquals(expectedScore, actualScore);

    testService.submitAnswer(0, 1);

    assertEquals(0, testService.getScore());
  }
}