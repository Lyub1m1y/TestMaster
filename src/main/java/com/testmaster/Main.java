package com.testmaster;

import com.testmaster.launcher.TestLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
    TestLauncher launcher = context.getBean(TestLauncher.class);

    launcher.startApp();
  }
}