package com.testmaster.controller;

import com.testmaster.model.Question;
import com.testmaster.model.Test;
import com.testmaster.service.TestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TestControllerTest {

  private TestService testService;
  private TestController controller;

  @BeforeEach
  void setUp() {
    testService = mock(TestService.class);
    controller = new TestController(testService);
  }

  @org.junit.jupiter.api.Test
  @DisplayName("Test selecting test from available tests")
  void startApp_testSelectingTestFromAvailableTests() {
    String input = "1\nMath\n1\nq\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    Test test = new Test("Math", List.of(new Question("2+2?", Arrays.asList("5", "22", "4"), 3)));
    List<Test> availableTests = Arrays.asList(test);

    when(testService.getAvailableTests()).thenReturn(availableTests);
    when(testService.getTestByName("Math")).thenReturn(test);

    controller.startApp();

    verify(testService, times(1)).testPreparation(test);
    verify(testService, times(1)).submitAnswer(0, 1);
    verify(testService, times(1)).getScore();
  }
}