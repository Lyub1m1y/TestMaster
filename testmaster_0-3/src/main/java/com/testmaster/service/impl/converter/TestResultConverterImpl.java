package com.testmaster.service.impl.converter;

import com.testmaster.model.TestResult;
import com.testmaster.service.LocalizationService;
import com.testmaster.service.TestResultConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TestResultConverterImpl implements TestResultConverter {

  private final LocalizationService localizationService;
  @Override
  public String convert(TestResult testResult) {
    return String.format(localizationService.getMessage("TEST_RESULT_MESSAGE"),
        testResult.getUser().getName(), testResult.getNumberOfCorrectAnswer(), testResult.getNumberOfQuestions());
  }
}
