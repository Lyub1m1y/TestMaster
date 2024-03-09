package com.testmaster.service.impl.io.system;

import com.testmaster.service.impl.io.OutputStreamProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.io.PrintStream;

@Service
public class SystemOutputService {

  private final OutputStream outputStream;

  public SystemOutputService(@Autowired OutputStreamProvider outputStreamProvider) {
    this.outputStream = outputStreamProvider.getOutputStream();
  }

  public void printMessage(String message) {
    ((PrintStream) outputStream).println(message);
  }
}
