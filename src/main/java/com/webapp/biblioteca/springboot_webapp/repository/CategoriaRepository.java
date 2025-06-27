package com.webapp.biblioteca.springboot_webapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.biblioteca.springboot_webapp.models.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
    

}
