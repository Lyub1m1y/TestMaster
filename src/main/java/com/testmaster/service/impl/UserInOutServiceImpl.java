package com.testmaster.service.impl;

import com.testmaster.service.UserInOutService;
import com.testmaster.service.UserInput;
import com.testmaster.service.UserOutput;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserInOutServiceImpl implements UserInOutService {

  private final UserInput userInput;
  private final UserOutput userOutput;

  @Override
  public String readInput() {
    return userInput.read();
  }

  @Override
  public void printOutput(String message) {
    userOutput.print(message);
  }
}
