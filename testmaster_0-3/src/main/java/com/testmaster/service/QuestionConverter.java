package com.testmaster.service;

import com.testmaster.model.Question;
import org.springframework.core.convert.converter.Converter;

public interface QuestionConverter extends Converter<Question, String> {
}
