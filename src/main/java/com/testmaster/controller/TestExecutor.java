package com.testmaster.controller;

import com.testmaster.model.Answer;
import com.testmaster.model.Question;
import com.testmaster.model.Score;
import com.testmaster.model.Test;
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

    List<Test> availableTests = testService.getAvailableTests();

    if (!availableTests.isEmpty()) {
      displayAvailableTests(availableTests);
      Test selectedTest = selectTest();
      if (selectedTest != null) {
        performTest(selectedTest);
      } else {
        userInOut.printOutput("Тест не найден.");
      }
    }
  }

  private Test selectTest() {
    userInOut.printOutput("Выберите тест: ");
    String testName = userInOut.readInput();
    return testService.getTestByName(testName);
  }

  private void displayAvailableTests(List<Test> availableTests) {
    userInOut.printOutput("Доступные тесты:");
    for (Test test : availableTests) {
      userInOut.printOutput(test.getTestName());
    }
  }

  private void performTest(Test selectedTest) {
    testService.testPreparation(selectedTest);
    List<Question> questions = selectedTest.getQuestions();
    for (int i = 0; i < questions.size(); i++) {
      Question question = questions.get(i);
      userInOut.printOutput((i + 1) + ". " + question.getText());
      List<String> options = question.getOptions();
      for (int j = 0; j < options.size(); j++) {
        userInOut.printOutput("\t" + (j + 1) + ") " + options.get(j));
      }

      Answer answer = new Answer();
      while (true) {
        userInOut.printOutput("Ваш ответ: ");
        String input = userInOut.readInput();
        try {
          answer.setAnswer(Integer.parseInt(input));
          if (answer.getAnswer() < 1 || answer.getAnswer() > options.size()) {
            throw new NumberFormatException();
          }
          break;
        } catch (NumberFormatException e) {
          userInOut.printOutput("Неверный ввод. Пожалуйста, введите число от 1 до "
              + options.size() + ".");
        }
      }

      testService.submitAnswer(i, answer);
    }

    Score score = testService.getScore();
    userInOut.printOutput("Результат: " + score.getNumberOfCorrectAnswer()
        + " из " + score.getNumberOfQuestions());
  }
}
