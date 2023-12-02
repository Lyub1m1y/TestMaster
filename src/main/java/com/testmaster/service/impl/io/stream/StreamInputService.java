package com.testmaster.service.impl.io.stream;

import com.testmaster.service.impl.io.InputService;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class StreamInputService implements InputService {

  private final Scanner scanner = new Scanner(System.in);

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
