package com.testmaster.service.impl;

import com.testmaster.model.Question;
import com.testmaster.model.TestResult;
import com.testmaster.model.UserTest;
import com.testmaster.service.InOutService;
import com.testmaster.service.QuestionConverter;
import com.testmaster.service.TestExecutionService;
import com.testmaster.utils.MessageUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestExecutionServiceImpl implements TestExecutionService {

  private final InOutService inOutService;
  private final QuestionConverter questionConverter;
  private final MessageUtils messageUtils;

  @Override
  public TestResult executeTest(UserTest selectedTest) {
    List<Question> questions = selectedTest.getQuestions();
    TestResult testResult = new TestResult(selectedTest);

    for (int i = 0; i < questions.size(); i++) {
      Question question = questions.get(i);
      inOutService.printMessage((i + 1) + ". "
          + questionConverter.convert(question));

      int answer = inOutService.readIntByInterval(1, question.getOptions().size(),
          messageUtils.getMessage("ASK_USER_ANSWER_MESSAGE"),
          String.format(messageUtils.getMessage("ANSWER_ERROR_MESSAGE"), question.getOptions().size()));
      inOutService.printMessageInterval();
      testResult.submitAnswer(question, answer, testResult);
    }

    return testResult;
  }
}
