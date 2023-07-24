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

    while (true) {
      System.out.println("Для выхода введите - q");
      System.out.println("1. Внутренние тесты");
      System.out.println("2. Тесты из каталога");

      String input = scanner.nextLine();
      if (input.equals("q")) {
        break;
      } else if (input.equals("1") || input.equals("2")) {
        testService.selectDirectoryTests(input);
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
      } else {
        System.out.println("Неверный ввод. Попробуйте еще раз.");
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
      System.out.print("Ваш ответ: ");
      int answerIndex = Integer.parseInt(scanner.nextLine());
      testService.submitAnswer(i, answerIndex);
    }

    int score = testService.getScore();
    System.out.println("Результат: " + score + " из " + questions.size());
  }
}
