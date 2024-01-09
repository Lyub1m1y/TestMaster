package com.testmaster.service.impl;

import com.testmaster.service.impl.io.SystemInOutService;
import com.testmaster.service.impl.io.InputStreamProvider;
import com.testmaster.service.impl.io.OutputStreamProvider;
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
  private InputStreamProvider inputStreamProviderMock;
  @Mock
  private OutputStreamProvider outputStreamProviderMock;
  @InjectMocks
  private SystemInOutService userInOutService;

  @DisplayName("readInput should return input")
  @Test
  void readInput_shouldReturnInput() {
    String input = "Test Input";
    when(inputStreamProviderMock.readLine()).thenReturn(input);

    String result = userInOutService.readLine();

    verify(inputStreamProviderMock).readLine();
    assertEquals(input, result);
  }

  @DisplayName("printOutput should print output")
  @Test
  void printOutput_shouldPrintOutput() {
    String message = "Test Message";

    userInOutService.printMessage(message);

    verify(outputStreamProviderMock).print(message);
  }
}