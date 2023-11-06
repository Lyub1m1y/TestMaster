package com.testmaster.service.stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.testmaster.service.impl.io.stream.StreamInputService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StreamInputServiceTest {

  private final InputStream systemIn = System.in;
  private ByteArrayInputStream testIn;
  private StreamInputService streamInputService;

  @BeforeEach
  public void setUp() {
//    testIn = new ByteArrayInputStream("Test Input".getBytes());
//    System.setIn(testIn);
//    streamInputService = new StreamInputService();
  }

  @AfterEach
  public void tearDown() {
    System.setIn(systemIn);
  }

  @Test
  void read_shouldReturnInput() {
//    String input = streamInputService.readLine();
//
//    assertEquals("Test Input", input);
  }
}
