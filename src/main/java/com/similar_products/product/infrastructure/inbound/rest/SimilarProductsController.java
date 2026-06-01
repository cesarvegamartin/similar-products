package com.similar_products.product.infrastructure.inbound.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.similar_products.product.application.GetSimilarProductsUseCase;
import com.similar_products.product.domain.Product;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class SimilarProductsController {

    private final GetSimilarProductsUseCase similarProducts;

    @GetMapping("/{productId}/similar")
    public List<Product> getSimilar(@PathVariable String productId) {
        return similarProducts.execute(productId);
    }
}
