package com.testmaster.service.impl;

import com.testmaster.service.UserInOutService;
import java.util.Scanner;

public class UserInOutServiceImpl implements UserInOutService {

  private final Scanner scanner = new Scanner(System.in);

  @Override
  public String readInput() {
    return scanner.nextLine();
  }

  @Override
  public void printOutput(String message) {
    System.out.println(message);
  }
}
