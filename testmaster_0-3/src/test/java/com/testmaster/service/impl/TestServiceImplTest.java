package com.testmaster.service.impl;

import com.testmaster.model.UserTest;
import com.testmaster.repository.UserTestRepository;
import com.testmaster.service.TestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TestServiceImpl.class})
class TestServiceImplTest {

    @MockBean
    private UserTestRepository userTestRepositoryMock;

    @Autowired
    private TestService testService;

    @Test
    @DisplayName("getAvailableTests should return available tests")
    void getAvailableTestsShouldReturnAvailableTests() {
        List<String> testNames = Arrays.asList("Test1", "Test2");
        when(userTestRepositoryMock.getTestNames()).thenReturn(testNames);

        List<String> availableTests = testService.getAvailableTests();

        assertEquals(testNames, availableTests);
        verify(userTestRepositoryMock).getTestNames();
    }

    @Test
    @DisplayName("getTestByName should return test by valid name")
    void getTestByNameShouldReturnTestByValidName() {
        String testName = "Test1";
        UserTest userTest = new UserTest(testName, List.of());
        when(userTestRepositoryMock.getTestByName(testName)).thenReturn(userTest);

        UserTest result = testService.getTestByName(testName);

        assertEquals(userTest, result);
        verify(userTestRepositoryMock).getTestByName(testName);
    }
}
