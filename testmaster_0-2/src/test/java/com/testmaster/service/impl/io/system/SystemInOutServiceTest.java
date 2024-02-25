package com.testmaster.service.impl.io.system;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SystemInOutServiceTest {

  @Mock
  private SystemInputService systemInputServiceMock;
  @Mock
  private SystemOutputService systemOutputServiceMock;
  @InjectMocks
  private SystemInOutService systemInOutServiceMock;

  @DisplayName("readInput should return input")
  @Test
  void readInput_shouldReturnInput() {
    String input = "Test Input";
    when(systemInputServiceMock.readLine()).thenReturn(input);

    String result = systemInOutServiceMock.readLine();

    verify(systemInputServiceMock).readLine();
    assertEquals(input, result);
  }

  @DisplayName("printOutput should print output")
  @Test
  void printOutput_shouldPrintOutput() {
    String message = "Test Message";

    systemInOutServiceMock.printMessage(message);

    verify(systemOutputServiceMock).printMessage(message);
  }
}