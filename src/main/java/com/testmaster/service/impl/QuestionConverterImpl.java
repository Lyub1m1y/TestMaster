package com.testmaster.service.impl;

import com.testmaster.model.Option;
import com.testmaster.model.Question;
import com.testmaster.service.QuestionConverter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionConverterImpl implements QuestionConverter {

  @Override
  public String convert(Question question) {
    StringBuilder sb = new StringBuilder();
    sb.append(question.getText()).append("\n");
    List<Option> options = question.getOptions();

    for (int j = 0; j < options.size(); j++) {
      sb.append("\t")
          .append(j + 1)
          .append(") ")
          .append(options.get(j).getOption())
          .append("\n");
    }

    return sb.toString();
  }
}
