package com.testmaster.exception;

public class TestRetrieveException extends RuntimeException {
    private static final String TEMPLATE_MESSAGE = "Failed to retrieve test files from %s: %s";

    public TestRetrieveException(String from, String path) {
        super(String.format(TEMPLATE_MESSAGE, from, path));
    }
}
