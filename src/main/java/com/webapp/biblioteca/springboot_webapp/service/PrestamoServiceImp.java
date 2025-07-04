package com.webapp.biblioteca.springboot_webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.biblioteca.springboot_webapp.models.Prestamo;
import com.webapp.biblioteca.springboot_webapp.repository.PrestamoRepository;

@Service
public class PrestamoServiceImp {
    @Autowired
    private PrestamoRepository prestamoRepository;


    public List<Prestamo> ListarPrestamos() {
        return (List<Prestamo>) prestamoRepository.findAll();
    }
    public List<Prestamo> obtenerUltimosPrestamos(int cantidad) {
        return prestamoRepository.findTop5ByOrderByFechaPrestamoDesc();
    }
    public List<Prestamo> obtenerPrestamosPorUsuario(int idUsuario) {
        return prestamoRepository.findByUsuarioIdUsuarioOrderByFechaPrestamoDesc(idUsuario);
    }
}
