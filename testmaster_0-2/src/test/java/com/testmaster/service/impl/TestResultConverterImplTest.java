//package com.testmaster.service.impl;
//
//import com.testmaster.config.Settings;
//import com.testmaster.model.TestResult;
//import com.testmaster.model.User;
//import com.testmaster.repository.impl.csv.impl.CsvFileProviderFromResources;
//import com.testmaster.service.impl.converter.TestResultConverterImpl;
//import com.testmaster.utils.MessageUtils;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@EnableConfigurationProperties(Settings.class)
//@SpringBootTest(classes = {MessageUtils.class, TestResultConverterImpl.class})
//class TestResultConverterImplTest {
//
//    @Mock
//    private TestResult testResultMock;
//    @Mock
//    private User userMock;
//    @Mock
//    private MessageUtils messageUtils;
//
//    @Autowired
//    private TestResultConverterImpl converter;
//
//    @Test
//    @DisplayName("Should convert method with valid TestResult")
//    void shouldConvertWithValidTestResult() {
//        when(userMock.getName()).thenReturn("John");
//
//        when(testResultMock.getUser()).thenReturn(userMock);
//        when(testResultMock.getNumberOfCorrectAnswer()).thenReturn(8);
//        when(testResultMock.getNumberOfQuestions()).thenReturn(10);
//
//        String result = converter.convert(testResultMock);
//
//        assertEquals("John, your test scores: 8 from 10.", result);
//    }
//
//    @Test
//    @DisplayName("Should convert method with zero correct answers")
//    void shouldConvertWithZeroCorrectAnswers() {
//        when(userMock.getName()).thenReturn("Alice");
//
//        when(testResultMock.getUser()).thenReturn(userMock);
//        when(testResultMock.getNumberOfCorrectAnswer()).thenReturn(0);
//        when(testResultMock.getNumberOfQuestions()).thenReturn(4);
//
//        String result = converter.convert(testResultMock);
//
//        assertEquals("Alice, your test scores: 0 from 4.", result);
//    }
//}