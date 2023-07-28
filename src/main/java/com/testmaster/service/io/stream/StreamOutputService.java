package com.testmaster.service.io.stream;

import com.testmaster.service.io.OutputService;

public class StreamOutputService implements OutputService {

  @Override
  public void print(String message) {
    System.out.println(message);
  }
}
