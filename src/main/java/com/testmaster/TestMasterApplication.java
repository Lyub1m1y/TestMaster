package com.testmaster;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class TestMasterApplication implements ApplicationRunner {

  private final TestLauncher testLauncher;

  public static void main(String[] args) {
    SpringApplication.run(TestMasterApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) {
    log.info("App started!");
    testLauncher.run();
  }
}
