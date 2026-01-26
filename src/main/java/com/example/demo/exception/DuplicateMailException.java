package com.example.demo.exception;

public class DuplicateMailException extends RuntimeException {
    public DuplicateMailException(String message) {
        super("Mail: " + message + " is already in use.");
    }
}
