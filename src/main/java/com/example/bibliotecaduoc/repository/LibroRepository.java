package com.example.bibliotecaduoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import java.util.List;
import com.example.bibliotecaduoc.model.Libro;

@Repository
// public class LibroRepository {

// // Arreglo que guardara todos los libros
// private List<Libro> listaLibros = new ArrayList<Libro>();
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    // Consulta nativa simple
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Libro findByEmailCustom(@Param("email") String email);
 
 
    // Consulta con JOINs nativos
    @Query(value = "SELECT u.* FROM users u INNER JOIN roles r ON u.role_id = r.id WHERE r.name = ?1", 
           nativeQuery = true)
    List<Libro> findByRoleName(String roleName);

    @Query("SELECT l FROM Libro l WHERE l.autor = :autor")
    List<Libro> buscarPorAutor1(@Param("autor") String autor);

    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE %:titulo%")
    List<Libro> buscarPorTitulo1(@Param("titulo") String titulo);


    // Metodo que retorna todoa los libros
    // public List<Libro> obtenerLibros() {
    // return listaLibros;
    // }

    // // Buscar un libro por su id
    // public Libro buscarPorId(int id) {
    // for (Libro libro : listaLibros) {
    // if (libro.getId() == id) {
    // return libro;
    // }
    // }
    // return null;
    // }

    // // Buscar un libro por su isbn
    // public Libro buscarPorIsbn(String isbn) {
    // for (Libro libro : listaLibros) {
    // if (libro.getIsbn().equals(isbn)) {
    // return libro;
    // }
    // }
    // return null;
    // }

    // public Libro guardar(Libro lib) {
    // // Libro libro = new Libro();
    // // libro.setId(lib.getId());
    // // libro.setIsbn(lib.getIsbn());
    // // libro.setTitulo(lib.getTitulo());
    // // libro.setAutor(lib.getAutor());
    // // libro.setFechaPublicacion(lib.getFechaPublicacion());
    // // libro.setEditorial(lib.getEditorial());

    // listaLibros.add(lib);
    // return lib;
    // }

    // public Libro actualizar(Libro lib) {
    // int id = 0;
    // int idPosicion = 0;

    // for (int i = 0; i < listaLibros.size(); i++) {
    // if (listaLibros.get(i).getId() == lib.getId()) {
    // id = lib.getId();
    // idPosicion = i;
    // }
    // }

    // Libro libro1 = new Libro();
    // libro1.setId(id);
    // libro1.setTitulo(lib.getTitulo());
    // libro1.setAutor(lib.getAutor());
    // libro1.setFechaPublicacion(lib.getFechaPublicacion());
    // libro1.setEditorial(lib.getEditorial());
    // libro1.setIsbn(lib.getIsbn());

    // listaLibros.set(idPosicion, libro1);
    // return libro1;
    // }

    // public void eliminar(int id) {
    // // alternativa 1
    // // Libro libro = buscarPorId(id);
    // // if (libro != null) {
    // // listaLibros.remove(libro);
    // // }
    // //
    // // // alternativa 2
    // // int idPosicion = 0;
    // // for (int i = 0; i < listaLibros.size(); i++) {
    // // if (listaLibros.get(i).getId() == id) {
    // // idPosicion = i;
    // // break;
    // // }
    // // }
    // // if (idPosicion > 0) {
    // // listaLibros.remove(idPosicion);
    // // }

    // // otra alternativa
    // listaLibros.removeIf(x -> x.getId() == id);
    // }


    // public int totalLibros() {
    // return listaLibros.size();
    // }

    // ESTE ES EL NUEVO MÉTODO QUE TE FALTABA PARA EL FILTRO
    List<Libro> findByAutorContainingIgnoreCase(String autor);

    default int totalLibros() {
        return (int) this.count(); // ← "this" se refiere a la instancia del repository
    }

}