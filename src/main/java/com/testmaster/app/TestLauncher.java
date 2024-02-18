package com.testmaster.app;

import com.testmaster.model.TestResult;
import com.testmaster.model.User;
import com.testmaster.model.UserTest;
import com.testmaster.service.*;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.testmaster.app.TestMasterConstants.EMPTY_MESSAGE;
import static java.util.Objects.isNull;

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
    try {
      User user = userService.initUser();
      inOutService.printMessage(EMPTY_MESSAGE);

      List<String> availableTests = testService.getAvailableTests();
      if (availableTests.isEmpty()) {
        inOutService.printMessage(NO_AVAILABLE_TESTS_ERROR_MESSAGE);
        return;
      }

      displayAvailableTests(availableTests);
      inOutService.printMessage(EMPTY_MESSAGE);
      UserTest selectedTest = selectTest();
      if (isNull(selectedTest)) {
        inOutService.printMessage(TEST_NOT_FOUND_ERROR_MESSAGE);
        return;
      }

      TestResult testResult = testExecutionService.executeTest(selectedTest);
      testResult.setUser(user);
      inOutService.printMessage(testResultConverter.convert(testResult));
    } catch (Exception ex) {
      inOutService.printMessage(ex.getMessage());
    }
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
