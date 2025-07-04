package com.webapp.biblioteca.springboot_webapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.biblioteca.springboot_webapp.models.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    List<Prestamo> findByFechaPrestamoBetweenAndEstado(LocalDate startDate, LocalDate endDate, String estado); 
    List<Prestamo> findTop5ByOrderByFechaPrestamoDesc();
    Prestamo findByIdPrestamo(int id);
    List<Prestamo> findByUsuarioIdUsuarioOrderByFechaPrestamoDesc(int idUsuario);

}
