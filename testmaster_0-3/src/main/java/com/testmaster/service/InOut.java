package com.testmaster.service;

public interface InOut {
    String readLine();

    String readLineWithMessage(String codeMessage);

    void printMessage(String message);

    void printMessageByCodeMessage(String codeMessage, Object... args);

    int readIntByIntervalAndCodeMessage(int min, int max, String codeMessage, String codeErrorMessage, Object... args);

    void printMessageInterval();

}
