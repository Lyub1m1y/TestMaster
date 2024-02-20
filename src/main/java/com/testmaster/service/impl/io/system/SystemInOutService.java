package com.testmaster.service.impl.io.system;

import com.testmaster.exception.InvalidNumberByIntervalException;
import com.testmaster.service.InOutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SystemInOutService implements InOutService {

  private final SystemInputService systemInputService;
  private final SystemOutputService systemOutputService;

  @Override
  public String readLine() {
    return systemInputService.readLine();
  }

  @Override
  public int readIntByInterval(int min, int max, String userInputPrompt, String errorMessage) {
    while (true) {
      try {
        printMessage(userInputPrompt);
        return systemInputService.readIntByInterval(min, max, errorMessage);
      } catch (InvalidNumberByIntervalException ex) {
        log.error(ExceptionUtils.getStackTrace(ex));
        printMessage(errorMessage);
      }
    }
  }

  @Override
  public void printMessage(String message) {
    systemOutputService.printMessage(message);
  }

  @Override
  public void printMessageInterval() {
    systemOutputService.printMessageInterval();
  }
}
