package com.testmaster.service.consoleinout;

import com.testmaster.service.UserInput;
import java.util.Scanner;

public class ConsoleUserInput implements UserInput {

  private final Scanner scanner = new Scanner(System.in);

  @Override
  public String read() {
    return scanner.nextLine();
  }
}
