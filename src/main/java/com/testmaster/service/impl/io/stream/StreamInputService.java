package com.testmaster.service.impl.io.stream;

import com.testmaster.service.impl.io.InputService;
import java.util.Scanner;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StreamInputService implements InputService {

  private final Scanner scanner;

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
