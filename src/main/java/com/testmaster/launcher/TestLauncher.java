package com.testmaster.launcher;

import com.testmaster.model.TestResult;
import com.testmaster.model.User;
import com.testmaster.model.UserTest;
import com.testmaster.service.*;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.testmaster.app.TestMasterConstants.EMPTY_MESSAGE;
import static com.testmaster.app.TestMasterConstants.TEST_NOT_FOUND_ERROR_MESSAGE;
import static com.testmaster.app.TestMasterConstants.NO_AVAILABLE_TESTS_ERROR_MESSAGE;
import static com.testmaster.app.TestMasterConstants.AVAILABLE_TESTS_MESSAGE;
import static com.testmaster.app.TestMasterConstants.SELECT_TEST_MESSAGE;
import static java.util.Objects.isNull;

@Component
@AllArgsConstructor
public class TestLauncher {

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

      TestResult testResult = testExecutionService.executionTest(selectedTest);
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
