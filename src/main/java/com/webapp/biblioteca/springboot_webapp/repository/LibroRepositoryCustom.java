package com.webapp.biblioteca.springboot_webapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.biblioteca.springboot_webapp.models.Libro;

public interface LibroRepositoryCustom {
     Page<Libro> buscarConFiltros(Integer year, String author, String editorial, String categoria, String nombre, Pageable pageable);
}
