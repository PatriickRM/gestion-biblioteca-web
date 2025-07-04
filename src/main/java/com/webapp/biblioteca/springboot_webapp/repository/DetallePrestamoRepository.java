package com.webapp.biblioteca.springboot_webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.biblioteca.springboot_webapp.models.DetallePrestamo;

public interface DetallePrestamoRepository extends JpaRepository<DetallePrestamo, Integer> {
    
}
