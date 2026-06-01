package com.similar_products.product.infrastructure.outbound.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.similar_products.product.application.port.out.SimilarProductsPort;

@Component
public class SimilarProductsApiClient implements SimilarProductsPort {

    private final WebClient webClient;

    public SimilarProductsApiClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:3001").build();
    }

    public List<String> getSimilarIds(String productId) {
        return webClient.get().uri("/product/{id}/similarids", productId)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<String>>() {}).block();
    }

}
