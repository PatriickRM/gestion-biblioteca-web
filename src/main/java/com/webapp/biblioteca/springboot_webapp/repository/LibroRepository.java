package com.webapp.biblioteca.springboot_webapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.webapp.biblioteca.springboot_webapp.models.Libro;

public interface LibroRepository extends CrudRepository<Libro, Integer>, LibroRepositoryCustom {
     Page<Libro> findAll(Pageable pageable);
     @Query("SELECT DISTINCT l.anio_publicacion FROM Libro l ORDER BY l.anio_publicacion DESC")
     List<Integer> obtenerAniosPublicacion();

     @Query("SELECT DISTINCT l.autor.nombre FROM Libro l ORDER BY l.autor.nombre ASC")
     List<String> obtenerAutores();

     @Query("SELECT DISTINCT l.editorial FROM Libro l ORDER BY l.editorial ASC")
     List<String> obtenerEditoriales();

     @Query("SELECT DISTINCT l.categoria.nombre_categoria FROM Libro l ORDER BY l.categoria.nombre_categoria ASC")
     List<String> obtenerCategorias();
}
