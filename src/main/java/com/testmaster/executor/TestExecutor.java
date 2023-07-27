package com.testmaster.executor;

import com.testmaster.model.Answer;
import com.testmaster.model.Option;
import com.testmaster.model.Question;
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
      performTest(selectedTest);
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

  private void performTest(UserTest selectedTest) {
    List<Question> questions = selectedTest.getQuestions();
    QuestionConverter converter = new QuestionConverterImpl(); // TODO mb spring inject

    for (int i = 0; i < questions.size(); i++) {
      Question question = questions.get(i);
      userInOut.printOutput((i + 1) + ". " + converter.convertToString(question));

//      Answer answer = new Answer();
//      while (true) {
//        userInOut.printOutput("Your answer: ");
//        String input = userInOut.readInput();
//        try {
//          answer.setAnswer(new Option(Integer.parseInt(input)));
//          if (answer.getAnswer() < 1 || answer.getAnswer() > options.size()) {
//            throw new NumberFormatException();
//          }
//          break;
//        } catch (NumberFormatException e) {
//          userInOut.printOutput("Invalid entry. Please enter a number between 1 and "
//              + options.size() + ".");
//        }
//      }
//
//      testService.submitAnswer(i, answer);

    }
  }

  private void displayResult() {

  }
}
