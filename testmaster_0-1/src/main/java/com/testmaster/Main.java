package com.testmaster;

import com.testmaster.config.AppConfig;
import com.testmaster.app.impl.ConsoleApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    App app = context.getBean(ConsoleApp.class);

    log.info("App started!");
    app.run();
  }
}
