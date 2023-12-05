package com.testmaster.service.impl;

import com.testmaster.model.Question;
import com.testmaster.model.TestResult;
import com.testmaster.model.UserTest;
import com.testmaster.repository.UserTestRepository;
import com.testmaster.service.InOutService;
import com.testmaster.service.QuestionConverter;
import com.testmaster.service.TestService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TestServiceImpl implements TestService {

  @NonNull
  private final UserTestRepository repository;
  @NonNull
  private final InOutService inOutService;
  @NonNull
  private final QuestionConverter questionConverter;

  @Override
  public List<String> getAvailableTests() {
    return repository.getTestNames();
  }

  @Override
  public UserTest getTestByName(String testName) {
    return repository.getTestByName(testName);
  }

  @Override
  public TestResult performTest(UserTest selectedTest) {
    List<Question> questions = selectedTest.getQuestions();
    TestResult testResult = new TestResult(selectedTest);

    for (int i = 0; i < questions.size(); i++) {
      Question question = questions.get(i);
      inOutService.printMessage((i + 1) + ". "
              + questionConverter.convert(question));

      int answer = inOutService.readIntByInterval(1, question.getOptions().size(), "Your answer: ");
      inOutService.printMessage("");
      testResult.submitAnswer(question, answer, testResult);
    }

    return testResult;
  }
}
