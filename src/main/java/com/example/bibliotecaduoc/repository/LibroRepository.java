package com.example.bibliotecaduoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bibliotecaduoc.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    // 🔍 Buscar por autor (ya lo tenías bien)
    List<Libro> findByAutorContainingIgnoreCase(String autor);

    // 🔍 Buscar por título
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // 🔥 Buscar por autor + título (NUEVO)
    List<Libro> findByAutorContainingIgnoreCaseAndTituloContainingIgnoreCase(String autor, String titulo);

}