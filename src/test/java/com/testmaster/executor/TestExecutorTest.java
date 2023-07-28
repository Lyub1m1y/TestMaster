package com.testmaster.executor;

import com.testmaster.model.Answer;
import com.testmaster.model.TestResult;
import com.testmaster.model.UserTest;
import com.testmaster.service.TestService;
import com.testmaster.service.UserInOutService;
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
class TestExecutorTest {

  @Mock
  private TestService testServiceMock;

  @Mock
  private UserInOutService userInOutMock;

  @InjectMocks
  private TestExecutor testExecutor;

  @Test
  void startApp_TestFound() {
    List<String> availableTests = Arrays.asList("Test1", "Test2");
    UserTest selectedTest = new UserTest("Test1", Collections.emptyList());
    TestResult testResult = new TestResult();

    when(testServiceMock.getAvailableTests()).thenReturn(availableTests);
    when(userInOutMock.readInput()).thenReturn("Test1");
    when(testServiceMock.getTestByName("Test1")).thenReturn(selectedTest);

    ArgumentCaptor<Answer> answerCaptor = ArgumentCaptor.forClass(Answer.class);

    testExecutor.startApp();

    assertAll(
        () -> verify(userInOutMock).printOutput("Select a test: "),
        () -> verify(userInOutMock).readInput(),
        () -> verify(testServiceMock, times(selectedTest.getQuestions().size()))
            .submitAnswer(any(), answerCaptor.capture(), any()),
        () -> verify(userInOutMock).printOutput("Result: " + testResult.getNumberOfCorrectAnswer()
            + " from " + testResult.getNumberOfQuestions()),
        () -> verify(userInOutMock, never()).printOutput("Test not found.")
    );
  }

  @Test
  void startApp_TestNotFound() {
    List<String> availableTests = Collections.emptyList();

    when(testServiceMock.getAvailableTests()).thenReturn(availableTests);
    when(userInOutMock.readInput()).thenReturn("NonExistentTest");

    testExecutor.startApp();

    assertAll(
        () -> verify(userInOutMock).printOutput("Select a test: "),
        () -> verify(userInOutMock).printOutput("Test not found."),
        () -> verify(testServiceMock, never()).submitAnswer(any(), any(), any())
    );
  }
}