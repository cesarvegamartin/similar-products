package com.similar_products.product.infrastructure.outbound.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.similar_products.infrastructure.rest.error.EntityNotFoundException;
import com.similar_products.infrastructure.rest.error.ExternalServiceException;
import com.similar_products.product.application.port.out.SimilarProductsPort;

import reactor.core.publisher.Mono;

@Component
public class SimilarProductsApiClient implements SimilarProductsPort {

    private final WebClient webClient;

    public SimilarProductsApiClient(@Qualifier("simuladoWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public List<String> getSimilarIds(String productId) {
        return webClient.get().uri("/product/{id}/similarids", productId)
            .retrieve()
            .onStatus(status -> status == HttpStatus.NOT_FOUND, response -> Mono.error(new EntityNotFoundException("Product with id " + productId + " not found")))
            .onStatus(status -> status.is5xxServerError(), response -> Mono.error(new ExternalServiceException("Error calling external service")))
            .bodyToMono(new ParameterizedTypeReference<List<String>>() {}).block();
    }

}
