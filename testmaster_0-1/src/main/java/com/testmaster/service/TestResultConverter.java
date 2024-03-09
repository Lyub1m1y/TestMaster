package com.testmaster.service;

import com.testmaster.model.TestResult;
import org.springframework.core.convert.converter.Converter;

public interface TestResultConverter extends Converter<TestResult, String> {
}
