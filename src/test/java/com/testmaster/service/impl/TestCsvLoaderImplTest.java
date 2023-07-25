package com.testmaster.service.impl;

import com.testmaster.model.Question;
import com.testmaster.model.CustomTest;
import java.util.List;

import com.testmaster.test.util.CustomTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCsvLoaderImplTest {

  private TestCsvLoaderImpl testCsvLoader;

  @BeforeEach
  void setUp() {
    testCsvLoader = new TestCsvLoaderImpl("");
  }

  @Test
  @DisplayName("Should return tests")
  void loadTestsFromResources_shouldReturnTests() {
    CustomTest expectedTest = CustomTestUtil.getTest();

    List<CustomTest> tests = testCsvLoader.loadTests();

    assertEquals(expectedTest, tests.get(0));
  }
}