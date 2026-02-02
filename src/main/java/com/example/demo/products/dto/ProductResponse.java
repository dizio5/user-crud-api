package com.example.demo.products.dto;

import java.math.BigDecimal;

public record ProductResponse(String name, Integer quantity, BigDecimal price) {}
