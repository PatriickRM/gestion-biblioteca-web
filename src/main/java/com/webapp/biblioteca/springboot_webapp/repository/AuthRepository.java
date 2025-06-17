package com.webapp.biblioteca.springboot_webapp.repository;

import com.webapp.biblioteca.springboot_webapp.models.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Trabajador, Integer> {
    Optional<Trabajador> findByUsuario(String usuario);

}