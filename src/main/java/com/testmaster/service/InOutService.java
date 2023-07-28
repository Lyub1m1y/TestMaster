package com.testmaster.service;

public interface InOutService {

  String readLine();

  int readIntByInterval(int min, int max, String message, String errorMessage);

  void printMessage(String message);
}
