package com.example.bibliotecaduoc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bibliotecaduoc.cliente.CompaneroIP;
import com.example.bibliotecaduoc.cliente.PokemonClient;
import com.example.bibliotecaduoc.model.Libro;
import com.example.bibliotecaduoc.repository.LibroRepository;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final PokemonClient pokemonClient;
    private final CompaneroIP companeroIP;

    // 🔥 Constructor con todas las dependencias
    public LibroService(LibroRepository libroRepository,
                        PokemonClient pokemonClient,
                        CompaneroIP companeroIP) {
        this.libroRepository = libroRepository;
        this.pokemonClient = pokemonClient;
        this.companeroIP = companeroIP;
    }

    // 🔥 API externa Pokémon
    public String obtenerPokemon(String nombre) {
        return pokemonClient.obtenerPokemon(nombre);
    }

    // 🔥 API de tu compañero
    public String obtenerUsuario(Long id) {
        return companeroIP.obtenerUsuario(id);
    }

    // 📚 Obtener todos los libros
    public List<Libro> getLibros() {
        return libroRepository.findAll();
    }

    // ➕ Guardar libro
    public Libro saveLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // 🔍 Buscar libro por ID
    public Libro getLibroId(int id) {
        return libroRepository.findById(id).orElse(null);
    }

    // 🔄 Actualizar libro
    public Libro updateLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // ❌ Eliminar libro
    public void deleteLibro(int id) {
        libroRepository.deleteById(id);
    }

    // 📊 Total de libros
    public int totalLibrosV2() {
        return (int) libroRepository.count();
    }

    // 🔍 Buscar por autor
    public List<Libro> buscarPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }

    // 🔍 Buscar por título
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // 🔥 Buscar por autor + título (COMBINADO)
    public List<Libro> buscarPorAutorYTitulo(String autor, String titulo) {
        return libroRepository
                .findByAutorContainingIgnoreCaseAndTituloContainingIgnoreCase(autor, titulo);
    }
}