package com.testmaster.service.impl.io.system;

import com.testmaster.service.impl.io.OutputStreamProvider;
import org.springframework.stereotype.Service;

@Service
public class SystemOutputService implements OutputStreamProvider {

  @Override
  public void print(String message) {
    System.out.println(message);
  }
}
