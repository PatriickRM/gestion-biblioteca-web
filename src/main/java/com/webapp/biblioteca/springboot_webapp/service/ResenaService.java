package com.webapp.biblioteca.springboot_webapp.service;

import java.util.List;
import com.webapp.biblioteca.springboot_webapp.models.Resena;

public interface ResenaService {
    void guardarResena(Resena resena);
    List<Resena> obtenerResenasPorLibro(int idLibro);
    List<Resena> obtenerResenasPorUsuario(int idUsuario);
    public Double promedioCalificacionPorLibro(int libroId);
}