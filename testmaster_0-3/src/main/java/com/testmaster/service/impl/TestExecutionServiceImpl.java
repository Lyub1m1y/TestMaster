package com.testmaster.service.impl;

import com.testmaster.model.Question;
import com.testmaster.model.TestResult;
import com.testmaster.model.UserTest;
import com.testmaster.service.InOut;
import com.testmaster.service.QuestionConverter;
import com.testmaster.service.TestExecutionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestExecutionServiceImpl implements TestExecutionService {

    private final InOut inOut;

    private final QuestionConverter questionConverter;

    @Override
    public TestResult executeTest(UserTest selectedTest) {
        List<Question> questions = selectedTest.getQuestions();
        TestResult testResult = new TestResult(selectedTest);

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            inOut.printMessage((i + 1) + ". "
                    + questionConverter.convert(question));

            int answer = inOut.readIntByIntervalAndCodeMessage(1, question.getOptions().size(),
                    "ask.user.answer.message",
                    "answer.error.message", question.getOptions().size());
            inOut.printMessageInterval();
            testResult.submitAnswer(question, answer, testResult);
        }

        return testResult;
    }
}
