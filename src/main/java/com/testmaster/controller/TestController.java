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

  public void start() {
    log.info("App started!");

    @Cleanup final Scanner scanner = new Scanner(System.in);

    List<Test> availableTests = testService.getAvailableTests();
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
          System.out.println("   " + (j + 1) + ". " + options.get(j));
        }
        System.out.print("Ваш ответ: ");
        int answerIndex = Integer.parseInt(scanner.nextLine()) - 1;
        testService.submitAnswer(i, answerIndex);
      } // TODO else

      int score = testService.getScore();
      System.out.println("Результат: " + score + " из " + questions.size());
    } else {
      System.out.println("Тест не найден.");
    }
  }

}
