package com.example.bibliotecaduoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // 🔥 ESTE TE FALTA (OBLIGATORIO)
    @Bean
    public WebClient pokemonWebClient() {
        return WebClient.builder()
                .baseUrl("https://pokeapi.co/api/v2")
                .build();
    }

    // 🔥 API de tu compañero
    @Bean
    public WebClient companeroWebClient() {
        return WebClient.builder()
                .baseUrl("http://192.168.111.220:8080/api/v1")
                .build();
    }
}