package com.testmaster.service.impl;

import com.testmaster.model.Question;
import com.testmaster.model.TestResult;
import com.testmaster.model.UserTest;
import com.testmaster.service.InOutService;
import com.testmaster.service.QuestionConverter;
import com.testmaster.service.TestExecutionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.testmaster.app.TestMasterConstants.ASK_USER_ANSWER_MESSAGE;
import static com.testmaster.app.TestMasterConstants.ANSWER_ERROR_MESSAGE;

@Service
@AllArgsConstructor
public class TestExecutionServiceImpl implements TestExecutionService {

  private final InOutService inOutService;
  private final QuestionConverter questionConverter;

  @Override
  public TestResult executeTest(UserTest selectedTest) {
    List<Question> questions = selectedTest.getQuestions();
    TestResult testResult = new TestResult(selectedTest);

    for (int i = 0; i < questions.size(); i++) {
      Question question = questions.get(i);
      inOutService.printMessage((i + 1) + ". "
          + questionConverter.convert(question));

      int answer = inOutService.readIntByInterval(1, question.getOptions().size(), ASK_USER_ANSWER_MESSAGE,
          String.format(ANSWER_ERROR_MESSAGE, question.getOptions().size()));
      inOutService.printMessageInterval();
      testResult.submitAnswer(question, answer, testResult);
    }

    return testResult;
  }
}
