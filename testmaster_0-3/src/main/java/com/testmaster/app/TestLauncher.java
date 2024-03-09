package com.testmaster.app;

import com.testmaster.model.TestResult;
import com.testmaster.model.User;
import com.testmaster.model.UserTest;
import com.testmaster.service.InOutService;
import com.testmaster.service.LocalizationService;
import com.testmaster.service.TestExecutionService;
import com.testmaster.service.TestResultConverter;
import com.testmaster.service.TestService;
import com.testmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class TestLauncher {

  private final UserService userService;
  private final TestService testService;
  private final TestExecutionService testExecutionService;
  private final InOutService inOutService;
  private final TestResultConverter testResultConverter;
  private final LocalizationService localizationService;

  public void run() {
    User user = userService.initUser();
    inOutService.printMessageInterval();

    List<String> availableTests = testService.getAvailableTests();
    if (availableTests.isEmpty()) {
      inOutService.printMessage(localizationService.getMessage("NO_AVAILABLE_TESTS_ERROR_MESSAGE"));
      return;
    }

    displayAvailableTests(availableTests);
    inOutService.printMessageInterval();
    UserTest selectedTest = selectTest();
    if (isNull(selectedTest)) {
      inOutService.printMessage(localizationService.getMessage("TEST_NOT_FOUND_ERROR_MESSAGE"));
      return;
    }

    TestResult testResult = testExecutionService.executeTest(selectedTest);
    testResult.setUser(user);
    inOutService.printMessage(testResultConverter.convert(testResult));
  }

  private void displayAvailableTests(List<String> availableTests) {
    inOutService.printMessage(localizationService.getMessage("AVAILABLE_TESTS_MESSAGE"));
    availableTests.forEach(inOutService::printMessage);
  }

  private UserTest selectTest() {
    inOutService.printMessage(localizationService.getMessage("SELECT_TEST_MESSAGE"));
    String testName = inOutService.readLine();
    return testService.getTestByName(testName);
  }
}
