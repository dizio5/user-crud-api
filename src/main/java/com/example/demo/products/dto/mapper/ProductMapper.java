package com.example.demo.products.dto.mapper;

import com.example.demo.products.dto.ProductResponse;
import com.example.demo.products.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getName(), product.getQuantity(), product.getPrice());
    }
}
