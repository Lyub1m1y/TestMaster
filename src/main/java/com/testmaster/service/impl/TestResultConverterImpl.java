package com.testmaster.service.impl;

import com.testmaster.model.TestResult;
import com.testmaster.service.TestResultConverter;

public class TestResultConverterImpl implements TestResultConverter {

  @Override
  public String convertTestResultToString(TestResult testResult) {
    StringBuilder sb = new StringBuilder();

    sb.append(testResult.getUser().getName())
        .append(", your test scores: ")
        .append(testResult.getNumberOfCorrectAnswer())
        .append(" from ")
        .append(testResult.getNumberOfQuestions())
        .append(".");

    return sb.toString();
  }
}