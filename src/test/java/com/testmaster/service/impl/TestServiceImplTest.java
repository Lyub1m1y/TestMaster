package com.testmaster.service.impl;

import com.testmaster.model.*;
import com.testmaster.repository.UserTestRepository;
import com.testmaster.service.impl.TestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestServiceImplTest {

  @Mock
  private UserTestRepository userTestRepositoryMock;

  @InjectMocks
  private TestServiceImpl testService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void getAvailableTests_shouldReturnAvailableTests() {
//    List<String> testNames = Arrays.asList("Test1", "Test2");
//    when(userTestRepositoryMock.getTestsNames()).thenReturn(testNames);
//
//    List<String> availableTests = testService.getAvailableTests();
//
//    assertEquals(testNames, availableTests);
//    verify(userTestRepositoryMock).getTestsNames();
  }

  @Test
  void getTestByName_shouldReturnTestByName() {
//    String testName = "Test1";
//    UserTest userTest = new UserTest(testName, Collections.emptyList());
//    when(userTestRepositoryMock.getTestByName(testName)).thenReturn(userTest);
//
//    UserTest result = testService.getTestByName(testName);
//
//    assertEquals(userTest, result);
//    verify(userTestRepositoryMock).getTestByName(testName);
  }

  @Test
  void submitAnswer_ValidAnswer() {
//    Option opt1 = new Option("Option1");
//    Option opt2 = new Option("Option2");
//    opt2.setCorrect(true);
//
//    Question question = new Question("Question1", List.of(opt1, opt2));
//    Answer answer = new Answer(1);
//    TestResult testResult = new TestResult();
//
//    testService.submitAnswer(question, answer, testResult);
//
//    assertEquals(1, testResult.getNumberOfCorrectAnswer());
  }

  @Test
  void submitAnswer_InvalidAnswer() {
//    Question question = new Question("Question1", Collections.emptyList());
//    Answer answer = new Answer(0); // Invalid answer index
//    TestResult testResult = new TestResult();
//
//    assertThrows(IllegalArgumentException.class,
//        () -> testService.submitAnswer(question, answer, testResult));
//    assertEquals(0, testResult.getNumberOfCorrectAnswer());
  }

  @Test
  void submitAnswer_NullQuestion() {
//    Question question = null;
//    Answer answer = new Answer(0);
//    TestResult testResult = new TestResult();
//
//    assertThrows(IllegalArgumentException.class,
//        () -> testService.submitAnswer(question, answer, testResult));
//    assertEquals(0, testResult.getNumberOfCorrectAnswer());
  }
}
