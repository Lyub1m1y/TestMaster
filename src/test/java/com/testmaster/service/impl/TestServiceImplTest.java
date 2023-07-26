package com.testmaster.service.impl;

import com.testmaster.model.Answer;
import com.testmaster.model.Question;
import com.testmaster.model.Score;
import com.testmaster.model.CustomTest;
import com.testmaster.service.TestLoader;
import java.util.List;

import com.testmaster.test.util.CustomTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestServiceImplTest {

  private TestServiceImpl testService;
  private CustomTest expectedTest;

  @BeforeEach
  void setUp() {
    TestLoader testLoader = new TestCsvLoaderImpl("");
    testService = new TestServiceImpl(testLoader);
    expectedTest = CustomTestUtil.getTest();
  }

//  @Test TODO
//  @DisplayName("Should return available tests")
//  void getAvailableTests_shouldReturnAvailableTests() {
//    List<CustomTest> availableTests = testService.getAvailableTests();
//
//    assertTrue(availableTests.size() > 0);
//    assertTrue(availableTests.stream().anyMatch(test -> test.getTestName().equals("Math")));
//  }

//  @Test TODO
//  @DisplayName("Should return test by name")
//  void getTestByName_shouldReturnTestByName() {
//    testService.getAvailableTests();
//    CustomTest actualTest = testService.getTestByName("Math");
//
//    assertEquals(expectedTest, actualTest);
//  }


  @Test
  @DisplayName("Should return correct score")
  void getScore_shouldReturnCorrectScore() {
    testService.testPreparation(expectedTest);
    testService.submitAnswer(0, new Answer(3));
    testService.submitAnswer(0, new Answer(2));
    int expectedScore = 2;

    Score actualScore = testService.getScore();

    assertEquals(expectedScore, actualScore.getNumberOfCorrectAnswer());
  }
}