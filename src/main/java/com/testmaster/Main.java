package com.testmaster;

import com.testmaster.executor.TestExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
    TestExecutor executor = context.getBean(TestExecutor.class);

    executor.startApp();
  }
}