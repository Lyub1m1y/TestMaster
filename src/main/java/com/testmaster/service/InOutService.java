package com.testmaster.service;

public interface InOutService {

  String readLine();

  int readIntByInterval(int min, int max, String message);

  void printMessage(String message);
}
