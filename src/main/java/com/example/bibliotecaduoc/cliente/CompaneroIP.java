package com.example.bibliotecaduoc.cliente;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CompaneroIP {

    private final WebClient webClient;

    // 🔥 IMPORTANTE: indicamos cuál WebClient usar
    public CompaneroIP(@Qualifier("companeroWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public String obtenerUsuario(Long id) {
        return webClient.get()
                .uri("/usuarios/" + id) // ⚠️ cambia si el endpoint es distinto
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}