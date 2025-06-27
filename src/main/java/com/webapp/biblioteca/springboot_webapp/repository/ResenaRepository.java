package com.webapp.biblioteca.springboot_webapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.webapp.biblioteca.springboot_webapp.models.Resena;

public interface ResenaRepository extends JpaRepository<Resena, Integer> {

     List<Resena> findByLibro_IdLibro(int idLibro); 
     List<Resena> findByUsuario_IdUsuario(int idUsuario);
     boolean existsByUsuario_IdUsuarioAndLibro_IdLibro(Integer idUsuario, Integer idLibro);

}