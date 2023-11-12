package com.testmaster;

import com.testmaster.config.AppConfig;
import com.testmaster.launcher.TestLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    TestLauncher launcher = context.getBean(TestLauncher.class);

    launcher.startApp();
  }
}
