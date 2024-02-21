package com.testmaster.service.impl.io.system;

import com.testmaster.exception.InvalidNumberByIntervalException;
import com.testmaster.service.impl.io.SystemInputStreamProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Slf4j
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
    try {
      int input = Integer.parseInt(scanner.nextLine());
      if (input >= min && input <= max) {
        return input;
      }
    } catch (NumberFormatException ex) {
      log.error(ExceptionUtils.getStackTrace(ex));
    }
    throw new InvalidNumberByIntervalException(errorMessage);
  }
}
