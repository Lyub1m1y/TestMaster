package com.testmaster.service.impl;

import com.testmaster.model.Option;
import com.testmaster.model.Question;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionConverterImplTest {

  @Test
  void convertToString_shouldReturnConvertedString() {
    Option opt1 = new Option("Paris");
    opt1.setCorrect(true);
    List<Option> options = Arrays.asList(
        opt1,
        new Option("Berlin"),
        new Option("London"),
        new Option("Rome")
    );
    Question question = new Question("What is the capital of France?", options);
    QuestionConverterImpl questionConverter = new QuestionConverterImpl();

    String result = questionConverter.convertToString(question);

    String expectedOutput = "What is the capital of France?\n" +
        "\t1) Paris\n" +
        "\t2) Berlin\n" +
        "\t3) London\n" +
        "\t4) Rome\n";

    assertEquals(expectedOutput, result);
  }
}
