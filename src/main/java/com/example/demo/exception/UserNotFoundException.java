package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long message) {
        super("User not found with id: " + message);
    }
}
