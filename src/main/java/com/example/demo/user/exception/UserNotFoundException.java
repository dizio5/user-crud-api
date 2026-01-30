package com.example.demo.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long message) {
        super("User not found with id: " + message);
    }

    public UserNotFoundException(String message) {
        super("User '" + message + "' not found.");
    }
}
