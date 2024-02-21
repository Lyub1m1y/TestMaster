package com.testmaster.app;

import com.testmaster.model.TestResult;
import com.testmaster.model.User;
import com.testmaster.model.UserTest;
import com.testmaster.service.*;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Slf4j
@Component
@AllArgsConstructor
public class TestLauncher {

  private static final String SELECT_TEST_MESSAGE = "Select a test: ";
  private static final String AVAILABLE_TESTS_MESSAGE = "Available tests:";
  private static final String TEST_NOT_FOUND_ERROR_MESSAGE = "Test not found.";
  private static final String NO_AVAILABLE_TESTS_ERROR_MESSAGE = "There are no tests available to take.";

  private final UserService userService;
  private final TestService testService;
  private final TestExecutionService testExecutionService;
  private final InOutService inOutService;
  private final TestResultConverter testResultConverter;

  public void run() {
    User user = userService.initUser();
    inOutService.printMessageInterval();

    List<String> availableTests = testService.getAvailableTests();
    if (availableTests.isEmpty()) {
      inOutService.printMessage(NO_AVAILABLE_TESTS_ERROR_MESSAGE);
      return;
    }

    displayAvailableTests(availableTests);
    inOutService.printMessageInterval();
    UserTest selectedTest = selectTest();
    if (isNull(selectedTest)) {
      inOutService.printMessage(TEST_NOT_FOUND_ERROR_MESSAGE);
      return;
    }

    TestResult testResult = testExecutionService.executeTest(selectedTest);
    testResult.setUser(user);
    inOutService.printMessage(testResultConverter.convert(testResult));
  }

  private void displayAvailableTests(List<String> availableTests) {
    inOutService.printMessage(AVAILABLE_TESTS_MESSAGE);
    availableTests.forEach(inOutService::printMessage);
  }

  private UserTest selectTest() {
    inOutService.printMessage(SELECT_TEST_MESSAGE);
    String testName = inOutService.readLine();
    return testService.getTestByName(testName);
  }
}
