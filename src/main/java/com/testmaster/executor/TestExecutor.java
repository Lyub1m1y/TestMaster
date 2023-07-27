package com.testmaster.executor;

import com.testmaster.model.UserTest;
import com.testmaster.service.TestService;
import com.testmaster.service.UserInOutService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TestExecutor {

  @NonNull
  private final TestService testService;
  @NonNull
  private final UserInOutService userInOut;

  public void startApp() {
    log.info("App started!");

    displayAvailableTests(testService.getAvailableTests());
//    UserTest selectedTest = selectTest();
//    if (selectedTest != null) {
//      performTest(selectedTest);
//    } else {
//      userInOut.printOutput("Test not found.");
//    }
  }

  private UserTest selectTest() {
    userInOut.printOutput("Select a test: ");
    String testName = userInOut.readInput();
    return testService.getTestByName(testName);
  }

  private void displayAvailableTests(List<String> availableTests) {
    if (!availableTests.isEmpty()) {
      userInOut.printOutput("Available tests:");
      for (String testName : availableTests) {
        userInOut.printOutput(testName);
      }
    }
  }

  private void performTest(UserTest selectedTest) {

  }

  private void displayResult() {

  }
}
