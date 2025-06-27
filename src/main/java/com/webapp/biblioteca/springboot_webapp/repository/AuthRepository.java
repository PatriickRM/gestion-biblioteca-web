package com.webapp.biblioteca.springboot_webapp.repository;

import com.webapp.biblioteca.springboot_webapp.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String usuario);
}