package com.webapp.biblioteca.springboot_webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.biblioteca.springboot_webapp.models.Resena;
import com.webapp.biblioteca.springboot_webapp.repository.ResenaRepository;

@Service
public class ResenaServiceImp implements ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Override
    public void guardarResena(Resena resena) {
        resenaRepository.save(resena);
    }

    @Override
    public List<Resena> obtenerResenasPorLibro(int idLibro) {
        return resenaRepository.findByLibro_IdLibro(idLibro);
    }

    @Override
    public List<Resena> obtenerResenasPorUsuario(int idUsuario) {
        return resenaRepository.findByUsuario_IdUsuario(idUsuario);
    }
}
