package com.testmaster.service.impl;

import com.testmaster.service.UserInput;
import com.testmaster.service.UserOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserInOutServiceImplTest {

  @Mock
  private UserInput userInputMock;

  @Mock
  private UserOutput userOutputMock;

  @InjectMocks
  private UserInOutServiceImpl userInOutService;

  @Test
  void readInput_shouldReturnInput() {
    String input = "Test Input";
    when(userInputMock.read()).thenReturn(input);

    String result = userInOutService.readInput();

    verify(userInputMock).read();
    assertEquals(input, result);
  }

  @Test
  void printOutput_shouldPrintOutput() {
    String message = "Test Message";

    userInOutService.printOutput(message);

    verify(userOutputMock).print(message);
  }
}