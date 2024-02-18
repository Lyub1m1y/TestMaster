package com.testmaster.service.impl.io.system;

import com.testmaster.service.impl.io.SystemInputStreamProvider;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class SystemInputStream implements SystemInputStreamProvider {

  @Override
  public InputStream getInputStream() {
    return System.in;
  }
}
