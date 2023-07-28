package com.testmaster.launcher;

import com.testmaster.model.TestResult;
import com.testmaster.model.UserTest;
import com.testmaster.service.TestService;
import com.testmaster.service.InOutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TestLauncherTest {

  @Mock
  private TestService testServiceMock;

  @Mock
  private InOutService userInOutMock;

  @InjectMocks
  private TestLauncher testLauncher;

  @Test
  void startApp_TestFound() {
    List<String> availableTests = Arrays.asList("Test1", "Test2");
    UserTest selectedTest = new UserTest("Test1", Collections.emptyList());
    TestResult testResult = new TestResult();

    when(testServiceMock.getAvailableTests()).thenReturn(availableTests);
    when(userInOutMock.readLine()).thenReturn("Test1");
    when(testServiceMock.getTestByName("Test1")).thenReturn(selectedTest);

    ArgumentCaptor<Answer> answerCaptor = ArgumentCaptor.forClass(Answer.class);

    testLauncher.startApp();

    assertAll(
        () -> verify(userInOutMock).printMessage("Select a test: "),
        () -> verify(userInOutMock).readLine(),
        () -> verify(testServiceMock, times(selectedTest.getQuestions().size()))
            .submitAnswer(any(), answerCaptor.capture(), any()),
        () -> verify(userInOutMock).printMessage("Result: " + testResult.getNumberOfCorrectAnswer()
            + " from " + testResult.getNumberOfQuestions()),
        () -> verify(userInOutMock, never()).printMessage("Test not found.")
    );
  }

  @Test
  void startApp_TestNotFound() {
    List<String> availableTests = Collections.emptyList();

    when(testServiceMock.getAvailableTests()).thenReturn(availableTests);
    when(userInOutMock.readLine()).thenReturn("NonExistentTest");

    testLauncher.startApp();

    assertAll(
        () -> verify(userInOutMock).printMessage("Select a test: "),
        () -> verify(userInOutMock).printMessage("Test not found."),
        () -> verify(testServiceMock, never()).submitAnswer(any(), any(), any())
    );
  }
}