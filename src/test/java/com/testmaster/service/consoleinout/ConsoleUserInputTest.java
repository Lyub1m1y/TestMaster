package com.testmaster.service.consoleinout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsoleUserInputTest {

  private final InputStream systemIn = System.in;
  private ByteArrayInputStream testIn;
  private ConsoleUserInput consoleUserInput;

  @BeforeEach
  public void setUp() {
    testIn = new ByteArrayInputStream("Test Input".getBytes());
    System.setIn(testIn);
    consoleUserInput = new ConsoleUserInput();
  }

  @AfterEach
  public void tearDown() {
    System.setIn(systemIn);
  }

  @Test
  void read_shouldReturnInput() {
    String input = consoleUserInput.read();

    assertEquals("Test Input", input);
  }
}
