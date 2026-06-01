package com.similar_products.product.application.port.out;

import com.similar_products.product.domain.Product;

public interface ProductDetailPort {
    Product getProductById(String productId);
}
