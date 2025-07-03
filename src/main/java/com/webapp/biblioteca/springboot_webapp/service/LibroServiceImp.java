package com.webapp.biblioteca.springboot_webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.webapp.biblioteca.springboot_webapp.models.Libro;
import com.webapp.biblioteca.springboot_webapp.repository.LibroRepository;

@Service
public class LibroServiceImp implements LibroService {
    
    @Autowired
    private LibroRepository libroRepository;

    @Override
    public void registrarLibro(Libro libro) {
       libroRepository.save(libro);
    }

    @Override
    public void actualizarLibro(Libro libro) {
        libroRepository.save(libro);
    }

    @Override
    public void eliminarLibro(Libro libro) {
        libroRepository.deleteById(libro.getIdLibro());
    }

    @Override
    public Libro buscarLibroPorId(Integer id) {
        return libroRepository.findById(id).orElse(null);
    }

    @Override
    public List<Libro> listarLibros() {
        return (List<Libro>) libroRepository.findAll();
    }

    @Override
    public Page<Libro> listarLibrosPaginados(Pageable pageable) {
       return libroRepository.findAll(pageable);
    }

    @Override
    public List<Integer> obtenerAniosPublicacion() {
        return libroRepository.obtenerAniosPublicacion();
    }

    @Override
    public List<String> obtenerAutores() {
       return libroRepository.obtenerAutores();
    }

    @Override
    public List<String> obtenerEditoriales() {
        return libroRepository.obtenerEditoriales();
    }

    @Override
    public List<String> obtenerCategorias() {
        return libroRepository.obtenerCategorias();
    }

    @Override
    public Page<Libro> buscarConFiltros(Integer year, String author, String editorial, String categoria, String nombre, Pageable pageable) {
        return libroRepository.buscarConFiltros(year, author, editorial, categoria, nombre, pageable);
    }

}
