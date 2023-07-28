package com.testmaster.launcher;

import com.testmaster.model.Question;
import com.testmaster.model.TestResult;
import com.testmaster.model.UserTest;
import com.testmaster.service.QuestionConverter;
import com.testmaster.service.TestService;
import com.testmaster.service.io.InOutService;
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

  public void startApp() {
    log.info("App started!");
    try {
      List<String> availableTests = testService.getAvailableTests();

      if (!availableTests.isEmpty()) {
        displayAvailableTests(availableTests);
        inOutService.printMessage("");
        UserTest selectedTest = selectTest();
        if (selectedTest != null) {
          TestResult testResult = performTest(selectedTest);
          displayTestResult(testResult);
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

  private TestResult performTest(UserTest selectedTest) {
    List<Question> questions = selectedTest.getQuestions();
    TestResult testResult = new TestResult(questions.size());

    for (int i = 0; i < questions.size(); i++) {
      Question question = questions.get(i);
      inOutService.printMessage((i + 1) + ". " + questionConverter.convertToString(question));

      int answer = inOutService.readIntByInterval(1, question.getOptions().size(), "Your answer: ",
          "Please enter a number between 1 and " + question.getOptions().size() + ".");
      testService.submitAnswer(question, answer, testResult);

    }

    return testResult;
  }

  private void displayTestResult(TestResult testResult) {
    inOutService.printMessage("Result: " + testResult.getNumberOfCorrectAnswer()
        + " from " + testResult.getNumberOfQuestions());
  }
}
