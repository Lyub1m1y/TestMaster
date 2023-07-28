package com.testmaster.service.io.stream;

import com.testmaster.service.io.InputService;
import java.util.Scanner;

public class StreamInputService implements InputService {

  private final Scanner scanner = new Scanner(System.in); // TODO DI

  @Override
  public String readLine() {
    return scanner.nextLine();
  }

  @Override
  public int readIntByInterval(int min, int max) throws RuntimeException {
    int input = Integer.parseInt(scanner.nextLine());
    if (input >= min && input <= max) {
      return input;
    } else {
      throw new RuntimeException();
    }
  }
}
