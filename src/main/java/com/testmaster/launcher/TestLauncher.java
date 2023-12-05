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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class TestLauncher {

  @NonNull
  private final UserService userService;
  @NonNull
  private final TestService testService;
  @NonNull
  private final InOutService inOutService;
  @NonNull
  private final TestResultConverter testResultConverter;

  public void startApp() {
    log.info("App started!");
    try {
      User user = userService.initUser();
      inOutService.printMessage("");

      List<String> availableTests = testService.getAvailableTests();
      if (!availableTests.isEmpty()) {
        displayAvailableTests(availableTests);
        inOutService.printMessage("");
        UserTest selectedTest = selectTest();
        if (selectedTest != null) {
          TestResult testResult = testService.performTest(selectedTest);
          testResult.setUser(user);
          inOutService.printMessage(testResultConverter.convert(testResult));
        } else {
          inOutService.printMessage("Test not found.");
        }
      } else {
        inOutService.printMessage("There are no tests available to take.");
      }
    } catch (Exception ex) {
      inOutService.printMessage(ex.getMessage());
    }
  }

  private void displayAvailableTests(List<String> availableTests) {
    inOutService.printMessage("Available tests:");
    for (String testName : availableTests) {
      inOutService.printMessage(testName);
    }
  }

  private UserTest selectTest() {
    inOutService.printMessage("Select a test: ");
    String testName = inOutService.readLine();
    return testService.getTestByName(testName);
  }
}
