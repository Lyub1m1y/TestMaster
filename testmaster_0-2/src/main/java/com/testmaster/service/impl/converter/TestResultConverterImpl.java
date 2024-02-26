package com.testmaster.service.impl.converter;

import com.testmaster.model.TestResult;
import com.testmaster.service.TestResultConverter;
import com.testmaster.utils.MessageUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TestResultConverterImpl implements TestResultConverter {

  private final MessageUtils messageUtils;
  @Override
  public String convert(TestResult testResult) {
    return String.format(messageUtils.getMessage("TEST_RESULT_MESSAGE"),
        testResult.getUser().getName(), testResult.getNumberOfCorrectAnswer(), testResult.getNumberOfQuestions());
  }
}
