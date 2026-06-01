package com.similar_products.product.infrastructure.outbound.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.similar_products.product.application.port.out.ProductDetailPort;
import com.similar_products.product.domain.Product;

@Component
public class ProductDetailApiClient implements ProductDetailPort {

    private final WebClient webClient;

    public ProductDetailApiClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:3001").build();
    }

    public Product getProductById(String productId) {
        return webClient.get().uri("/product/{id}", productId)
            .retrieve()
            .bodyToMono(Product.class).block();
    }

}
