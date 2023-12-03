package com.testmaster.service.impl.io;

import com.testmaster.exception.InvalidNumberByIntervalException;
import com.testmaster.service.InOutService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class InOutServiceImpl implements InOutService {

  private final InputService inputService;
  private final OutputService outputService;

  @Override
  public String readLine() {
    return inputService.readLine();
  }

  @Override
  public int readIntByInterval(int min, int max, String message) {
    while (true) {
      try {
        printMessage(message);
        return inputService.readIntByInterval(min, max);
      } catch (InvalidNumberByIntervalException ex) {
        log.error(ex.getMessage());
        printMessage(ex.getMessage());
      }
    }
  }

  @Override
  public void printMessage(String message) {
    outputService.print(message);
  }
}
