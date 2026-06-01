package com.similar_products.product.application;

import java.util.List;

import com.similar_products.product.domain.Product;

public interface GetSimilarProductsUseCase {
    List<Product> execute(String productId);
}
