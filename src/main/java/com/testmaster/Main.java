package com.testmaster;

import com.testmaster.config.AppConfig;
import com.testmaster.launcher.TestLauncher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    TestLauncher launcher = context.getBean(TestLauncher.class);

    log.info("App started!");
    launcher.run();
  }
}
