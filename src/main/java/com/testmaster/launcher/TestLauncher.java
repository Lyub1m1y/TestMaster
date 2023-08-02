package com.testmaster.launcher;

import com.testmaster.model.Question;
import com.testmaster.model.TestResult;
import com.testmaster.model.User;
import com.testmaster.model.UserTest;
import com.testmaster.service.InOutService;
import com.testmaster.service.QuestionConverter;
import com.testmaster.service.TestResultConverter;
import com.testmaster.service.TestService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TestLauncher {

  @NonNull
  private final TestService testService;
  @NonNull
  private final InOutService inOutService;
  @NonNull
  private final QuestionConverter questionConverter;
  @NonNull
  private final TestResultConverter testResultConverter;

  public void startApp() {
    log.info("App started!");
    try {
      User user = initUser();
      inOutService.printMessage("");

      List<String> availableTests = testService.getAvailableTests();

      if (!availableTests.isEmpty()) {
        displayAvailableTests(availableTests);
        inOutService.printMessage("");
        UserTest selectedTest = selectTest();
        if (selectedTest != null) {
          TestResult testResult = performTest(selectedTest);
          testResult.setUser(user);
          inOutService.printMessage(testResultConverter.convertTestResultToString(testResult));
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

  private User initUser() {
    inOutService.printMessage("Your name: ");
    String name = inOutService.readLine();
    inOutService.printMessage("Your surname: ");
    String surname = inOutService.readLine();

    return new User(name, surname);
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

  private TestResult performTest(UserTest selectedTest) {
    List<Question> questions = selectedTest.getQuestions();
    TestResult testResult = new TestResult(selectedTest);

    for (int i = 0; i < questions.size(); i++) {
      Question question = questions.get(i);
      inOutService.printMessage((i + 1) + ". "
          + questionConverter.convertQuestionToString(question));

      int answer = inOutService.readIntByInterval(1, question.getOptions().size(), "Your answer: ",
          "Please enter a number between 1 and " + question.getOptions().size() + ".");
      inOutService.printMessage("");
      testService.submitAnswer(question, answer, testResult);
    }

    return testResult;
  }
}
