package com.testmaster.service.impl;

import com.testmaster.model.*;
import com.testmaster.repository.UserTestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestServiceImplTest {

  @Mock
  private UserTestRepository userTestRepositoryMock;

  @InjectMocks
  private TestServiceImpl testService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @DisplayName("getAvailableTests should return available tests")
  @Test
  void getAvailableTests_shouldReturnAvailableTests() {
    List<String> testNames = Arrays.asList("Test1", "Test2");
    when(userTestRepositoryMock.getTestsNames()).thenReturn(testNames);

    List<String> availableTests = testService.getAvailableTests();

    assertEquals(testNames, availableTests);
    verify(userTestRepositoryMock).getTestsNames();
  }

  @DisplayName("getTestByName should return test by valid name")
  @Test
  void getTestByName_shouldReturnTestByValidName() {
    String testName = "Test1";
    UserTest userTest = new UserTest(testName, List.of());
    when(userTestRepositoryMock.getTestByName(testName)).thenReturn(userTest);

    UserTest result = testService.getTestByName(testName);

    assertEquals(userTest, result);
    verify(userTestRepositoryMock).getTestByName(testName);
  }
}
