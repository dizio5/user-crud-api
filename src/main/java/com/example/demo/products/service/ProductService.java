package com.example.demo.products.service;

import com.example.demo.products.dto.ProductResponse;
import com.example.demo.products.dto.mapper.ProductMapper;
import com.example.demo.products.repository.ProductRepository;
import com.example.demo.user.exception.UserNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResponse> findOrders(Jwt jwt) {
        productRepository.findByMail(jwt.getSubject())
                .orElseThrow(() -> new UserNotFoundException(jwt.getSubject()));

        // TODO: Encontrar todos los productos de ese usuario, si es que existe
        return null;
    }

}
