package com.webapp.biblioteca.springboot_webapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.biblioteca.springboot_webapp.models.Libro;

public interface LibroService {
    //Metodos
    void registrarLibro(Libro libro);
    void actualizarLibro(Libro libro);
    void eliminarLibro(Libro libro);
    Libro buscarLibroPorId(Integer id);
    List<Libro> listarLibros();
    Page<Libro> listarLibrosPaginados(Pageable pageable);
    public List<Integer> obtenerAniosPublicacion();
    public List<String> obtenerAutores();
    public List<String> obtenerEditoriales();
    public List<String> obtenerCategorias();
    long contarLibros();
    Page<Libro> buscarConFiltros(Integer year, String author, String editorial, String categoria, String nombre, Pageable pageable);
}
