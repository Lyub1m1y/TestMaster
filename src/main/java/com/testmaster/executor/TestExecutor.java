package com.testmaster.executor;

import com.testmaster.model.Answer;
import com.testmaster.model.Question;
import com.testmaster.model.TestResult;
import com.testmaster.model.UserTest;
import com.testmaster.service.QuestionConverter;
import com.testmaster.service.TestService;
import com.testmaster.service.UserInOutService;
import com.testmaster.service.impl.QuestionConverterImpl;
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
    UserTest selectedTest = selectTest();
    if (selectedTest != null) {
      TestResult testResult = performTest(selectedTest);
      displayTestResult(testResult);
    } else {
      userInOut.printOutput("Test not found.");
    }
  }


  private void displayAvailableTests(List<String> availableTests) {
    if (!availableTests.isEmpty()) {
      userInOut.printOutput("Available tests:");
      for (String testName : availableTests) {
        userInOut.printOutput(testName);
      }
    }
  }

  private UserTest selectTest() {
    userInOut.printOutput("Select a test: ");
    String testName = userInOut.readInput();
    return testService.getTestByName(testName);
  }

  private TestResult performTest(UserTest selectedTest) {
    List<Question> questions = selectedTest.getQuestions();
    TestResult testResult = new TestResult();
    testResult.setNumberOfQuestions(questions.size());
    QuestionConverter converter = new QuestionConverterImpl();

    for (int i = 0; i < questions.size(); i++) {
      Question question = questions.get(i);
      userInOut.printOutput((i + 1) + ". " + converter.convertToString(question));

      while (true) {
        Answer answer = new Answer();
        userInOut.printOutput("Your answer: ");
        String input = userInOut.readInput();
        try {
          answer.setAnswer(Integer.parseInt(input) - 1);
          testService.submitAnswer(question, answer, testResult);
          break;
        } catch (Exception ex) {
          log.error("Please enter a number between 1 and " + question.getOptions().size() + ".");
        }
      }
    }
    return testResult;
  }

  private void displayTestResult(TestResult testResult) {
    userInOut.printOutput("Result: " + testResult.getNumberOfCorrectAnswer()
        + " from " + testResult.getNumberOfQuestions());
  }
}
