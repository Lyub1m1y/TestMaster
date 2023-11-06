package com.testmaster.service.impl;

import com.testmaster.service.impl.io.InOutServiceImpl;
import com.testmaster.service.impl.io.InputService;
import com.testmaster.service.impl.io.OutputService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InOutServiceImplTest {

  @Mock
  private InputService inputServiceMock;

  @Mock
  private OutputService outputServiceMock;

  @InjectMocks
  private InOutServiceImpl userInOutService;

  @Test
  void readInput_shouldReturnInput() {
//    String input = "Test Input";
//    when(inputServiceMock.readLine()).thenReturn(input);
//
//    String result = userInOutService.readLine();
//
//    verify(inputServiceMock).readLine();
//    assertEquals(input, result);
  }

  @Test
  void printOutput_shouldPrintOutput() {
//    String message = "Test Message";
//
//    userInOutService.printMessage(message);
//
//    verify(outputServiceMock).print(message);
  }
}