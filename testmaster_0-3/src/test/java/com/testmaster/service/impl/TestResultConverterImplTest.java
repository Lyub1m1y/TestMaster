package com.testmaster.service.impl;

import com.testmaster.config.Settings;
import com.testmaster.model.TestResult;
import com.testmaster.model.User;
import com.testmaster.service.TestResultConverter;
import com.testmaster.service.impl.converter.TestResultConverterImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@EnableConfigurationProperties(Settings.class)
@ImportAutoConfiguration(MessageSourceAutoConfiguration.class)
@SpringBootTest(classes = {TestResultConverterImpl.class, LocalizationServiceImpl.class})
class TestResultConverterImplTest {

    @Mock
    private TestResult testResultMock;

    @Mock
    private User userMock;

    @Autowired
    private TestResultConverter converter;

    @Test
    @DisplayName("Should convert method with valid TestResult")
    void shouldConvertWithValidTestResult() {
        when(userMock.getName()).thenReturn("John");
        when(testResultMock.getUser()).thenReturn(userMock);
        when(testResultMock.getNumberOfCorrectAnswer()).thenReturn(8);
        when(testResultMock.getNumberOfQuestions()).thenReturn(10);

        assertEquals("John, your test result: 8 from 10.", converter.convert(testResultMock));
    }

    @Test
    @DisplayName("Should convert method with zero correct answers")
    void shouldConvertWithZeroCorrectAnswers() {
        when(userMock.getName()).thenReturn("Alice");
        when(testResultMock.getUser()).thenReturn(userMock);
        when(testResultMock.getNumberOfCorrectAnswer()).thenReturn(0);
        when(testResultMock.getNumberOfQuestions()).thenReturn(4);

        String result = converter.convert(testResultMock);

        assertEquals("Alice, your test result: 0 from 4.", result);
    }
}