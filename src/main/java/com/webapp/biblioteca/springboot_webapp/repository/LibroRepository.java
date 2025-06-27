package com.webapp.biblioteca.springboot_webapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.webapp.biblioteca.springboot_webapp.models.Libro;

public interface LibroRepository extends CrudRepository<Libro, Integer> {
     Page<Libro> findAll(Pageable pageable);
}
