package com.similar_products.product.application;

import java.util.List;

import org.springframework.stereotype.Service;

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
        return similarIds.stream().map(productDetailPort::getProductById).toList();
    }    
    
}
