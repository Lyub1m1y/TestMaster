package com.testmaster;

import com.testmaster.app.TestLauncher;
import com.testmaster.config.Settings;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@RequiredArgsConstructor
@SpringBootApplication
@EnableConfigurationProperties(Settings.class)
public class TestMasterApplication implements ApplicationRunner {

  private final TestLauncher testLauncher;

  public static void main(String[] args) {
    SpringApplication.run(TestMasterApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) {
    testLauncher.run();
  }
}
