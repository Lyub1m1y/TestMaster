package com.testmaster.service.impl.io.system;

import com.testmaster.exception.InvalidNumberByIntervalException;
import com.testmaster.service.impl.io.SystemInputStreamProvider;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SystemInputService {

  private final Scanner scanner;

  public SystemInputService(SystemInputStreamProvider systemInputStreamProvider) {
    this.scanner = new Scanner(systemInputStreamProvider.getInputStream());
  }

  public String readLine() {
    return scanner.nextLine();
  }

  public int readIntByInterval(int min, int max, String errorMessage) {
    int input = Integer.parseInt(scanner.nextLine());
    if (input >= min && input <= max) {
      return input;
    }
      throw new InvalidNumberByIntervalException(errorMessage);
  }
}
