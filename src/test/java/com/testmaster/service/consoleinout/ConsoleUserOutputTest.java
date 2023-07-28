package com.testmaster.service.consoleinout;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleUserOutputTest {

  @Test
  void print_shouldPrint() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream customPrintStream = new PrintStream(outputStream);
    System.setOut(customPrintStream);

    ConsoleUserOutput consoleUserOutput = new ConsoleUserOutput();
    String message = "Test Output";

    consoleUserOutput.print(message);

    System.setOut(System.out);

    String printedMessage = outputStream.toString().trim();
    assertEquals(message, printedMessage);
  }
}
