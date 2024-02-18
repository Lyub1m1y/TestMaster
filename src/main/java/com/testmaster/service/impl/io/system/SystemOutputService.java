package com.testmaster.service.impl.io.system;

import com.testmaster.service.impl.io.SystemOutputStreamProvider;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.io.PrintStream;

@Service
public class SystemOutputService {

  private final OutputStream outputStream;

  public SystemOutputService(SystemOutputStreamProvider systemOutputStreamProvider) {
    this.outputStream = systemOutputStreamProvider.getOutputStream();
  }

  public void printMessage(String message) {
    ((PrintStream) outputStream).println(message);
  }
}
