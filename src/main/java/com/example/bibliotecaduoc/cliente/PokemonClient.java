package com.example.bibliotecaduoc.cliente;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PokemonClient {

    private final WebClient webClient;

    public PokemonClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public String obtenerPokemon(String nombre) {
        return webClient.get()
                .uri("/pokemon/" + nombre)
                .retrieve()
                .bodyToMono(String.class) // recibimos JSON como texto
                .block();
    }
}