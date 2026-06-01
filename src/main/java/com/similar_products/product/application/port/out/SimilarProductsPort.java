package com.similar_products.product.application.port.out;

import java.util.List;

public interface SimilarProductsPort {
    List<String> getSimilarIds(String productId);
}
