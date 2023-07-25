package com.testmaster;

import com.testmaster.controller.TestExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
    TestExecutor controller = context.getBean(TestExecutor.class);

    controller.startApp();
  }
}