package com.testmaster.service;

public interface InOutService {

  String readLine();

  int readIntByInterval(int min, int max, String userInputPrompt, String errorMessage);

  void printMessage(String message);

  void printMessageInterval();
}
