package com.testmaster.service;

import com.testmaster.model.TestResult;

public interface TestResultConverter {

  String convertTestResultToString(TestResult testResult);
}
