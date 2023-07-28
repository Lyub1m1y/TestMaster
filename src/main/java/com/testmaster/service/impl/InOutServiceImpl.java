package com.testmaster.service.impl;

import com.testmaster.service.io.InOutService;
import com.testmaster.service.io.InputService;
import com.testmaster.service.io.OutputService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class InOutServiceImpl implements InOutService {

  private final InputService inputService;
  private final OutputService outputService;

  @Override
  public String readLine() {
    return inputService.readLine();
  }

  @Override
  public int readIntByInterval(int min, int max, String message, String errorMessage) {
    while (true) {
      try {
        printMessage(message);
        return inputService.readIntByInterval(min, max);
      } catch (RuntimeException ex) {
        log.error(errorMessage, ex);
        printMessage(errorMessage);
      }
    }
  }

  @Override
  public void printMessage(String message) {
    outputService.print(message);
  }
}