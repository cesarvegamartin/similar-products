package com.similar_products.product.infrastructure.inbound.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.similar_products.product.application.GetSimilarProductsUseCase;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Validated
public class SimilarProductsController {

    private final GetSimilarProductsUseCase similarProducts;

    @GetMapping("/{productId}/similar")
    public List<GetSimilarProductsResponse> getSimilar(
        @PathVariable
        @Pattern(regexp = "\\d+", message = "Product ID must be a numeric string")
        String productId
    ) {
        return similarProducts.execute(productId)
            .stream()
            .map(GetSimilarProductsResponse::from)
            .toList();
    }
}
