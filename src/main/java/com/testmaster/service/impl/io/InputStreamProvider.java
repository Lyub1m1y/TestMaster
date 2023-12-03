package com.testmaster.service.impl.io;

public interface InputStreamProvider {

  String readLine();

  int readIntByInterval(int min, int max);
}
