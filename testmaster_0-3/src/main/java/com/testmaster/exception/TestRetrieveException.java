package com.testmaster.exception;

public class TestRetrieveException extends RuntimeException {
    private static final String MESSAGE = "Failed to retrieve test files from %s: %s";

    public TestRetrieveException(String from, String path) {
        super(String.format(MESSAGE, from, path));
    }
}
