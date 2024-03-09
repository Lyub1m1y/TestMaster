package com.testmaster.service.impl;

import com.testmaster.model.Option;
import com.testmaster.model.Question;
import com.testmaster.service.impl.converter.QuestionConverterImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionConverterImplTest {

  @DisplayName("convertToString should return converted string")
  @Test
  void convertToString_shouldReturnConvertedString() {
    Option opt = new Option("Paris");
    opt.setCorrect(true);
    List<Option> options = Arrays.asList(
        opt,
        new Option("Berlin"),
        new Option("London"),
        new Option("Rome")
    );
    Question question = new Question("What is the capital of France?", options);
    QuestionConverterImpl questionConverter = new QuestionConverterImpl();

    String result = questionConverter.convert(question);

    String expectedOutput = "What is the capital of France?\n" +
        "\t1) Paris\n" +
        "\t2) Berlin\n" +
        "\t3) London\n" +
        "\t4) Rome\n";

    assertEquals(expectedOutput, result);
  }
}
