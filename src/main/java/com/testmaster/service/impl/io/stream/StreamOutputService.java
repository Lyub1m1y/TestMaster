package com.testmaster.service.impl.io.stream;

import com.testmaster.service.impl.io.OutputService;
import org.springframework.stereotype.Service;

@Service
public class StreamOutputService implements OutputService {

  @Override
  public void print(String message) {
    System.out.println(message);
  }
}
