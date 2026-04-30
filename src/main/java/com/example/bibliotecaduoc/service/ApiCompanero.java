package com.example.bibliotecaduoc.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

 
@Service // Importante para que Spring gestione esta clase
public class ApiCompanero {
 
 
    private final WebClient webClient;
 
 
    // Spring buscará el Bean que definiste en WebClientConfig y lo pondrá aquí
    public ApiCompanero(WebClient webClient) {
        this.webClient = webClient;
    }
 
 
    public Mono<String> getLibrosDeCompañero() {
        return this.webClient.get()
                .uri("/api/v1/libros") // Asegúrate de que esta sea la ruta exacta de tu amigo
                .retrieve()
                .bodyToMono(String.class);
    }
}
