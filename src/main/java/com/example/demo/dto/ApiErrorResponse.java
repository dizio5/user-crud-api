package com.example.demo.dto;

import java.time.Instant;

public record ApiErrorResponse (
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
){
}
