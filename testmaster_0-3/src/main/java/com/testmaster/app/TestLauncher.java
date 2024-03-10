package com.testmaster.app;

import com.testmaster.model.TestResult;
import com.testmaster.model.User;
import com.testmaster.model.UserTest;
import com.testmaster.service.InOut;
import com.testmaster.service.TestExecutionService;
import com.testmaster.service.TestResultConverter;
import com.testmaster.service.TestService;
import com.testmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Component
public class TestLauncher {

    private final UserService userService;

    private final TestService testService;

    private final TestExecutionService testExecutionService;

    private final TestResultConverter testResultConverter;

    private final InOut inOut;

    public void run() {
        User user = userService.initUser();
        inOut.printMessageInterval();

        List<String> availableTests = testService.getAvailableTests();
        if (availableTests.isEmpty()) {
            inOut.printMessageByCodeMessage("no.available.tests.error.message");
            return;
        }

        displayAvailableTests(availableTests);
        inOut.printMessageInterval();
        UserTest selectedTest = selectTest();
        if (isNull(selectedTest)) {
            inOut.printMessageByCodeMessage("test.not.found.error.message");
            return;
        }

        TestResult testResult = testExecutionService.executeTest(selectedTest);
        testResult.setUser(user);
        inOut.printMessage(testResultConverter.convert(testResult));
    }

    private void displayAvailableTests(List<String> availableTests) {
        inOut.printMessageByCodeMessage("available.tests.message");
        availableTests.forEach(inOut::printMessage);
    }

    private UserTest selectTest() {
        inOut.printMessageByCodeMessage("select.test.message");
        String testName = inOut.readLine();
        return testService.getTestByName(testName);
    }
}
