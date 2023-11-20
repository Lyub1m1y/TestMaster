package com.testmaster.service.impl;

import com.testmaster.model.TestResult;
import com.testmaster.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestResultConverterImplTest {

    @Mock
    TestResult testResultMock;
    @Mock
    User userMock;

    private final TestResultConverterImpl converter = new TestResultConverterImpl();

    @Test
    @DisplayName("Should convert method with valid TestResult")
    void shouldConvertWithValidTestResult() {
        when(userMock.getName()).thenReturn("John");

        when(testResultMock.getUser()).thenReturn(userMock);
        when(testResultMock.getNumberOfCorrectAnswer()).thenReturn(8);
        when(testResultMock.getNumberOfQuestions()).thenReturn(10);

        String result = converter.convert(testResultMock);

        assertEquals("John, your test scores: 8 from 10.", result);
    }

    @Test
    @DisplayName("Should convert method with zero correct answers")
    void shouldConvertWithZeroCorrectAnswers() {
        when(userMock.getName()).thenReturn("Alice");

        when(testResultMock.getUser()).thenReturn(userMock);
        when(testResultMock.getNumberOfCorrectAnswer()).thenReturn(0);
        when(testResultMock.getNumberOfQuestions()).thenReturn(4);

        String result = converter.convert(testResultMock);

        assertEquals("Alice, your test scores: 0 from 4.", result);
    }
}