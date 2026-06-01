package com.similar_products.product.application;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.similar_products.infrastructure.rest.error.ResourceNotFoundException;
import com.similar_products.product.application.port.out.ProductDetailPort;
import com.similar_products.product.application.port.out.SimilarProductsPort;
import com.similar_products.product.domain.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetSimilarProductsService implements GetSimilarProductsUseCase {

    private final SimilarProductsPort similarProductsPort;
    private final ProductDetailPort productDetailPort;

    public List<Product> execute(String productId) {
        List<String> similarIds = similarProductsPort.getSimilarIds(productId);

        if (similarIds.isEmpty()) {
            return List.of();
        }

        return similarIds.stream()
            .map(id -> {
                try {
                    return productDetailPort.getProductById(id);
                } catch (Exception e) {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .toList();
    }    
}
