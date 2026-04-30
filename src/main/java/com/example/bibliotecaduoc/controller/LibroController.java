package com.example.bibliotecaduoc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bibliotecaduoc.dto.CreateLibroRequest;
import com.example.bibliotecaduoc.dto.UpdateLibroRequest;
import com.example.bibliotecaduoc.exception.ResourceNotFoundException;
import com.example.bibliotecaduoc.mapper.LibroMapper;
import com.example.bibliotecaduoc.model.Libro;
import com.example.bibliotecaduoc.service.ApiCompanero;
import com.example.bibliotecaduoc.service.LibroService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // 🔥 ENDPOINT EXTERNO (Pokemon API)
    @GetMapping("/pokemon/{nombre}")
    public ResponseEntity<String> obtenerPokemon(@PathVariable String nombre) {
        return ResponseEntity.ok(libroService.obtenerPokemon(nombre));
    }

    // 🔥 LISTAR LIBROS + FILTROS (CORREGIDO)
    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros(
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String titulo) {

        if (autor != null && titulo != null) {
            return ResponseEntity.ok(libroService.buscarPorAutorYTitulo(autor, titulo));
        }

        if (autor != null) {
            return ResponseEntity.ok(libroService.buscarPorAutor(autor));
        }

        if (titulo != null) {
            return ResponseEntity.ok(libroService.buscarPorTitulo(titulo));
        }

        return ResponseEntity.ok(libroService.getLibros());
    }

    // BUSCAR POR ID
    @GetMapping("{id}")
    public ResponseEntity<Libro> buscarLibro(@PathVariable int id) {
        Libro libro = libroService.getLibroId(id);

        if (libro == null) {
            throw new ResourceNotFoundException("Libro no encontrado para id: " + id);
        }

        return ResponseEntity.ok(libro);
    }

    // CREAR LIBRO
    @PostMapping
    public ResponseEntity<Libro> agregarLibro(@Valid @RequestBody CreateLibroRequest request) {
        Libro nuevoLibro = libroService.saveLibro(LibroMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
    }

    // ACTUALIZAR LIBRO
    @PutMapping("{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable int id,
            @Valid @RequestBody UpdateLibroRequest request) {

        Libro libroActualizado = libroService.updateLibro(LibroMapper.toModel(id, request));
        return ResponseEntity.ok(libroActualizado);
    }

    // ❌ ELIMINAR LIBRO
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable int id) {
        libroService.deleteLibro(id);
        return ResponseEntity.noContent().build();
    }

    // 📊 TOTAL DE LIBROS
    @GetMapping("/total")       
    public ResponseEntity<Integer> totalLibros() {
        return ResponseEntity.ok(libroService.totalLibrosV2());
    }

    @GetMapping("/usuario/{id}")
        public ResponseEntity<String> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.obtenerUsuario(id));
        }

        @RestController
        @RequestMapping("/api/v1/externo") // Una ruta diferente para no chocar con tus libros
        public class ExternoController {
 
 
        private final ApiCompanero apiCompanero;
 
 
        // Inyectamos el servicio que creamos antes
        public ExternoController(ApiCompanero apiCompanero) {
                this.apiCompanero = apiCompanero;
        }
 
 
        @GetMapping("/libros-companero")
        public Mono<String> obtenerLibrosDeAmigo() {
                // Llamamos al método que usa el WebClient
                return apiCompanero.getLibrosDeCompañero();
                }
        }


}