package com.similar_products.product.infrastructure.inbound.rest;

import com.similar_products.product.domain.Product;

public record GetSimilarProductsResponse(
        String id,
        String name,
        Double price,
        Boolean availability
) {
    public static GetSimilarProductsResponse from(Product product) {
        return new GetSimilarProductsResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getAvailability()
        );
    }
}
