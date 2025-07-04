package com.webapp.biblioteca.springboot_webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.biblioteca.springboot_webapp.models.Roles;

public interface RolRepository extends JpaRepository<Roles, Integer> {
    public Roles findByNombreRol(String nombreRol);
     List<Roles> findByNombreRolIn(List<String> nombres);
}