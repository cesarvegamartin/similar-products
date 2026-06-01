package com.similar_products.product.infrastructure.outbound.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.similar_products.infrastructure.rest.error.EntityNotFoundException;
import com.similar_products.infrastructure.rest.error.ExternalServiceException;
import com.similar_products.product.application.port.out.ProductDetailPort;
import com.similar_products.product.domain.Product;

import reactor.core.publisher.Mono;

@Component
public class ProductDetailApiClient implements ProductDetailPort {

    private final WebClient webClient;

    public ProductDetailApiClient(
        @Qualifier("simuladoWebClient") WebClient webClient
    ) {
        this.webClient = webClient;
    }

    public Product getProductById(String productId) {
        return webClient.get().uri("/product/{id}", productId)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new EntityNotFoundException("Product with id " + productId + " not found")))
            .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new ExternalServiceException("Product API error")))
            .bodyToMono(Product.class)
            .block();
    }

}
