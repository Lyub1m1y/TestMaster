package com.testmaster.controller;

import com.testmaster.model.Question;
import com.testmaster.model.Test;
import com.testmaster.service.TestService;
import java.util.List;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class TestController {

  private TestService testService;

  public void startApp() {
    log.info("App started!");
    @Cleanup final Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Для выхода введите - q");
      System.out.println("1. Внутренние тесты");
      System.out.println("2. Тесты из каталога");

      String input = scanner.nextLine();
      if (!input.equals("q")) {
        testService.selectDirectoryTests(input); // TODO сделать проверку
      } else {
        break;
      }

      List<Test> availableTests = testService.getAvailableTests();
      if (!availableTests.isEmpty()) {
        System.out.println("Доступные тесты:");
        for (Test test : availableTests) {
          System.out.println(test.getTestName());
        }

        System.out.print("Выберите тест: ");
        String testName = scanner.nextLine();
        Test selectedTest = testService.getTestByName(testName);

        if (selectedTest != null) {
          testService.startTest(selectedTest);
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
        } else {
          System.out.println("Тест не найден.");
        }
      }
    }
  }
}
