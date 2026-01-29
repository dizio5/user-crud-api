package com.example.demo.user.exception;

public class DuplicateMailException extends RuntimeException {
    public DuplicateMailException(String message) {
        super("Mail: " + message + " is already in use.");
    }
}
