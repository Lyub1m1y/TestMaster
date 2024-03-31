package com.testmaster.service.impl;

import com.testmaster.model.Option;
import com.testmaster.model.Question;
import com.testmaster.service.QuestionConverter;
import com.testmaster.service.impl.converter.QuestionConverterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {QuestionConverterImpl.class})
class QuestionConverterImplTest {

    @Autowired
    private QuestionConverter questionConverter;

    private Question question;

    @BeforeEach
    void setUp() {
        Option opt = new Option("Paris");
        opt.setCorrect(true);
        List<Option> options = Arrays.asList(
                opt,
                new Option("Berlin"),
                new Option("London"),
                new Option("Rome")
        );
        question = new Question("What is the capital of France?", options);

    }

    @Test
    @DisplayName("convertToString should return converted string")
    void convertToStringShouldReturnConvertedString() {
        String expectedOutput = "What is the capital of France?\n" +
                "\t1) Paris\n" +
                "\t2) Berlin\n" +
                "\t3) London\n" +
                "\t4) Rome\n";

        String result = questionConverter.convert(question);
        assertEquals(expectedOutput, result);
    }
}
