package com.testmaster.launcher;

import com.testmaster.model.TestResult;
import com.testmaster.model.User;
import com.testmaster.model.UserTest;
import com.testmaster.service.InOutService;
import com.testmaster.service.TestResultConverter;
import com.testmaster.service.TestService;
import java.util.List;

import com.testmaster.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TestLauncher {

  @NonNull
  private final UserService userService;
  @NonNull
  private final TestService testService;
  @NonNull
  private final InOutService inOutService;
  @NonNull
  private final TestResultConverter testResultConverter;

  private static final String EMPTY_MESSAGE = "";
  private static final String TEST_NOT_FOUND_MESSAGE = "Test not found.";
  private static final String NO_AVAILABLE_TESTS_MESSAGE = "There are no tests available to take.";
  private static final String AVAILABLE_TESTS_MESSAGE = "Available tests:";
  private static final String SELECT_TEST_MESSAGE = "Select a test: ";

  public void run() {
    try {
      User user = userService.initUser();
      inOutService.printMessage(EMPTY_MESSAGE);

      List<String> availableTests = testService.getAvailableTests();
      if (!availableTests.isEmpty()) {
        displayAvailableTests(availableTests);
        inOutService.printMessage(EMPTY_MESSAGE);
        UserTest selectedTest = selectTest();
        if (selectedTest != null) {
          TestResult testResult = testService.performTest(selectedTest);
          testResult.setUser(user);
          inOutService.printMessage(testResultConverter.convert(testResult));
        } else {
          inOutService.printMessage(TEST_NOT_FOUND_MESSAGE);
        }
      } else {
        inOutService.printMessage(NO_AVAILABLE_TESTS_MESSAGE);
      }
    } catch (Exception ex) {
      inOutService.printMessage(ex.getMessage());
    }
  }

  private void displayAvailableTests(List<String> availableTests) {
    inOutService.printMessage(AVAILABLE_TESTS_MESSAGE);
    for (String testName : availableTests) {
      inOutService.printMessage(testName);
    }
  }

  private UserTest selectTest() {
    inOutService.printMessage(SELECT_TEST_MESSAGE);
    String testName = inOutService.readLine();
    return testService.getTestByName(testName);
  }
}
