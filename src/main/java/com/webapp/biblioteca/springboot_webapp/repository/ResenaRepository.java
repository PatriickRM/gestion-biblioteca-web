package com.webapp.biblioteca.springboot_webapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webapp.biblioteca.springboot_webapp.models.Resena;

public interface ResenaRepository extends JpaRepository<Resena, Integer> {

     List<Resena> findByLibro_IdLibro(int idLibro); 
     List<Resena> findByUsuario_IdUsuario(int idUsuario);
     boolean existsByUsuario_IdUsuarioAndLibro_IdLibro(Integer idUsuario, Integer idLibro);
     List<Resena> findByLibro_IdLibroOrderByFechaDesc(int idLibro);
     @Query("SELECT AVG(r.calificacion) FROM Resena r WHERE r.libro.idLibro = :idLibro")
     Double obtenerPromedioPorLibro(@Param("idLibro") Integer idLibro);
}