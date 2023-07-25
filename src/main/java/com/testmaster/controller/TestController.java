package com.testmaster.controller;

import com.testmaster.model.Question;
import com.testmaster.model.Test;
import com.testmaster.service.TestService;
import java.util.List;
import java.util.Scanner;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TestController {

  @NonNull
  private TestService testService;
  private Scanner scanner;

  public void startApp() {
    log.info("App started!");
    scanner = new Scanner(System.in);

    List<Test> availableTests = testService.getAvailableTests();

    if (!availableTests.isEmpty()) {
      displayAvailableTests(availableTests);
      Test selectedTest = selectTest();
      if (selectedTest != null) {
        performTest(selectedTest);
      } else {
        System.out.println("Тест не найден.");
      }
    }
    scanner.close();
  }

  private Test selectTest() {
    System.out.print("Выберите тест: ");
    String testName = scanner.nextLine();
    return testService.getTestByName(testName);
  }

  private void displayAvailableTests(List<Test> availableTests) {
    System.out.println("Доступные тесты:");
    for (Test test : availableTests) {
      System.out.println(test.getTestName());
    }
  }

  private void performTest(Test selectedTest) {
    testService.testPreparation(selectedTest);
    List<Question> questions = selectedTest.getQuestions();
    for (int i = 0; i < questions.size(); i++) {
      Question question = questions.get(i);
      System.out.println((i + 1) + ". " + question.getText());
      List<String> options = question.getOptions();
      for (int j = 0; j < options.size(); j++) {
        System.out.println("\t" + (j + 1) + ") " + options.get(j));
      }

      int answerIndex;
      while (true) {
        System.out.print("Ваш ответ: ");
        String input = scanner.nextLine();
        try {
          answerIndex = Integer.parseInt(input);
          if (answerIndex < 1 || answerIndex > options.size()) {
            throw new NumberFormatException();
          }
          break;
        } catch (NumberFormatException e) {
          System.out.println("Неверный ввод. Пожалуйста, введите число от 1 до "
              + options.size() + ".");
        }
      }

      testService.submitAnswer(i, answerIndex);
    }

    int score = testService.getScore();
    System.out.println("Результат: " + score + " из " + questions.size());
  }
}
