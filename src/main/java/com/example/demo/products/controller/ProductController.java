package com.example.demo.products.controller;

import com.example.demo.products.dto.ProductResponse;
import com.example.demo.products.repository.ProductRepository;
import com.example.demo.products.service.ProductService;
import com.example.demo.products.entity.Product;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductResponse> getProducts(@AuthenticationPrincipal Jwt jwt) {
        return productService.findOrders(jwt);
    }
}
