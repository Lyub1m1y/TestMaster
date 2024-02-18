package com.testmaster.service.impl.io.system;

import com.testmaster.exception.InvalidNumberByIntervalException;
import com.testmaster.service.InOutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SystemInOutService implements InOutService {

  private final SystemInputService systemInputService;
  private final SystemOutputService systemOutputService;

  @Autowired
  public SystemInOutService(SystemInputService systemInputService, SystemOutputService systemOutputService) {
    this.systemInputService = systemInputService;
    this.systemOutputService = systemOutputService;
  }

  @Override
  public String readLine() {
    return systemInputService.readLine();
  }

  @Override
  public int readIntByInterval(int min, int max, String errorMessage) {
    while (true) {
      try {
        return systemInputService.readIntByInterval(min, max, errorMessage);
      } catch (InvalidNumberByIntervalException ex) {
        log.error(ex.getMessage());
        printMessage(ex.getMessage());
      }
    }
  }

  @Override
  public void printMessage(String message) {
    systemOutputService.printMessage(message);
  }
}
