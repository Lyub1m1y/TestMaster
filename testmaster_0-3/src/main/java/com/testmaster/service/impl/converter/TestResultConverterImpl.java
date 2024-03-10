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
        return localizationService.getMessage("test.result.message",
                testResult.getUser().getName(), testResult.getNumberOfCorrectAnswer(),
                testResult.getNumberOfQuestions());
    }
}
