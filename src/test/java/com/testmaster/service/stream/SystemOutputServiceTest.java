package com.testmaster.service.stream;

import com.testmaster.service.impl.io.OutputStreamProvider;
import com.testmaster.service.impl.io.system.SystemOutputService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SystemOutputServiceTest {

  private static final String TEST_MESSAGE = "Hello, World!";

  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream originalSystemOut = System.out;

  private OutputStreamProvider outputStreamProvider;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outputStream));
    outputStreamProvider = new SystemOutputService(outputStream);
  }

  @AfterEach
  void tearDown() {
    System.out.flush();
    System.setOut(originalSystemOut);
  }

  @DisplayName("print should print message to console")
  @Test
  void print_ShouldPrintMessageToConsole() {
    outputStreamProvider.print(TEST_MESSAGE);

    String printedMessage = outputStream.toString().trim();

    assertEquals(TEST_MESSAGE, printedMessage);
  }

  @DisplayName("print should print empty string when message is null")
  @Test
  void print_ShouldPrintEmptyStringWhenMessageIsNull() {
    outputStreamProvider.print(null);

    String printedMessage = outputStream.toString().trim();

    assertEquals("null", printedMessage);
  }

  @DisplayName("print should print empty string when message is empty")
  @Test
  void print_ShouldPrintEmptyStringWhenMessageIsEmpty() {
    outputStreamProvider.print("");

    String printedMessage = outputStream.toString().trim();

    assertEquals("", printedMessage);
  }
}