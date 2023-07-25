package com.testmaster;

import com.testmaster.model.Answer;
import com.testmaster.model.CustomTest;
import com.testmaster.model.Score;
import com.testmaster.service.TestService;
import com.testmaster.service.UserInOutService;
import com.testmaster.test.util.CustomTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.mockito.Mockito.*;

class TestExecutorTest {

  private TestService testService;
  private UserInOutService userInOut;
  private TestExecutor executor;

  @BeforeEach
  void setUp() {
    testService = mock(TestService.class);
    userInOut = mock(UserInOutService.class);
    executor = new TestExecutor(testService, userInOut);
  }

  @Test
  @DisplayName("Test selecting test from available tests")
  void startApp_testSelectingTestFromAvailableTests() {
    CustomTest test = CustomTestUtil.getTest();
    List<CustomTest> availableTests = List.of(test);

    when(testService.getAvailableTests()).thenReturn(availableTests);
    when(userInOut.readInput()).thenReturn("Math", "3");
    when(testService.getTestByName("Math")).thenReturn(test);
    when(testService.getScore()).thenReturn(new Score());

    executor.startApp();

    verify(testService, times(1)).testPreparation(test);
    verify(testService, times(1)).getScore();
  }
}
