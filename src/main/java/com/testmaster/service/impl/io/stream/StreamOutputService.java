package com.testmaster.service.impl.io.stream;

import com.testmaster.service.impl.io.OutputStreamProvider;
import org.springframework.stereotype.Service;

@Service
public class StreamOutputService implements OutputStreamProvider {

  @Override
  public void print(String message) {
    System.out.println(message);
  }
}
