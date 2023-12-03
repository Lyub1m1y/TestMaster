package com.testmaster.service.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Scanner;

import com.testmaster.service.impl.io.InputStreamProvider;
import com.testmaster.service.impl.io.stream.StreamInputService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StreamInputServiceTest {

  @DisplayName("Should return input string")
  @Test
  void readLine_ShouldReturnInputString() {
    String inputString = "Hello, World!";
    Scanner scannerMock = new Scanner(inputString);
    InputStreamProvider inputStreamProvider = new StreamInputService(scannerMock);

    String result = inputStreamProvider.readLine();

    assertEquals(inputString, result);
  }

  @DisplayName("Should return input value within the interval")
  @Test
  void readIntByInterval_WithValidInput_ShouldReturnInputValue() {
    int min = 1;
    int max = 10;
    int inputValue = 5;
    String inputString = String.valueOf(inputValue);
    Scanner scannerMock = new Scanner(inputString);
    InputStreamProvider inputStreamProvider = new StreamInputService(scannerMock);

    int result = inputStreamProvider.readIntByInterval(min, max);

    assertEquals(inputValue, result);
  }

  @DisplayName("Should throw RuntimeException for invalid input value")
  @Test
  void readIntByInterval_WithInvalidInput_ShouldThrowRuntimeException() {
    int min = 1;
    int max = 10;
    int inputValue = 15;
    String inputString = String.valueOf(inputValue);
    Scanner scannerMock = new Scanner(inputString);
    InputStreamProvider inputStreamProvider = new StreamInputService(scannerMock);

    assertThrows(
            RuntimeException.class,
            () -> inputStreamProvider.readIntByInterval(min, max)
    );
  }
}
