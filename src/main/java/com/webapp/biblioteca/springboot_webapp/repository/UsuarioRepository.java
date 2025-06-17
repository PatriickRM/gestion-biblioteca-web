package com.webapp.biblioteca.springboot_webapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.biblioteca.springboot_webapp.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
