package com.testmaster.controller;

import com.testmaster.service.TestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class TestController {

  private TestService testService;

  public void start() {
    log.info("App started!");
  }

}
