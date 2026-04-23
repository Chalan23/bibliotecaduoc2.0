package com.example.bibliotecaduoc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bibliotecaduoc.model.Libro;
import com.example.bibliotecaduoc.repository.LibroRepository;

/**
 * Lógica de negocio de Libros
 */
@Service
public class LibroService {

    private final LibroRepository libroRepository;

    // Constructor injection
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    /**
     * Obtener todos los libros
     */
    public List<Libro> getLibros() {
        return libroRepository.findAll();
    }

    /**
     * Guardar libro
     */
    public Libro saveLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    /**
     * Buscar libro por ID
     */
    public Libro getLibroId(int id) {
        return libroRepository.findById(id).orElse(null);
    }

    /**
     * Actualizar libro
     */
    public Libro updateLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    /**
     * Eliminar libro por ID
     */
    public void deleteLibro(int id) {
        libroRepository.deleteById(id);
    }

    /**
     * Total de libros
     */
    public int totalLibrosV2() {
        return (int) libroRepository.count();
    }

    /**
     * Buscar libros por autor (FILTRO)
     */
    public List<Libro> buscarPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }

        /**
     * Buscar libros por título (FILTRO)
     */
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    /**
     * Buscar libros por autor y título (FILTRO combinado)
     */
    public List<Libro> buscarPorAutorYTitulo(String autor, String titulo) {
        return libroRepository
                .findByAutorContainingIgnoreCaseAndTituloContainingIgnoreCase(autor, titulo);
    }
}