package com.testmaster;

import com.testmaster.controller.TestController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
    TestController controller = context.getBean("testController", TestController.class);

    controller.startApp();
  }
}