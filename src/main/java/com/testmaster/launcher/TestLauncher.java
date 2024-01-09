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

import static com.testmaster.app.TestMasterConstants.EMPTY_MESSAGE;
import static com.testmaster.app.TestMasterConstants.TEST_NOT_FOUND_ERROR_MESSAGE;
import static com.testmaster.app.TestMasterConstants.NO_AVAILABLE_TESTS_ERROR_MESSAGE;
import static com.testmaster.app.TestMasterConstants.AVAILABLE_TESTS_MESSAGE;
import static com.testmaster.app.TestMasterConstants.SELECT_TEST_MESSAGE;

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
          inOutService.printMessage(TEST_NOT_FOUND_ERROR_MESSAGE);
        }
      } else {
        inOutService.printMessage(NO_AVAILABLE_TESTS_ERROR_MESSAGE);
      }
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
