package com.testmaster;

import com.testmaster.controller.TestController;
import com.testmaster.service.TestService;
import com.testmaster.service.impl.TestServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

    TestService service = new TestServiceImpl();
    TestController controller = new TestController(service);
    controller.start();
  }
}