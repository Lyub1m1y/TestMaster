package com.testmaster.service.impl.io.system;

import com.testmaster.service.impl.io.SystemOutputStreamProvider;
import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Component
public class SystemOutputStream implements SystemOutputStreamProvider {

  @Override
  public OutputStream getOutputStream() {
    return System.out;
  }
}
