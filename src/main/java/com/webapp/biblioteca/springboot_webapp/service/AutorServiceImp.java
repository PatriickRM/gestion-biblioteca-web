package com.webapp.biblioteca.springboot_webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.biblioteca.springboot_webapp.models.Autor;
import com.webapp.biblioteca.springboot_webapp.repository.AutorRepository;

@Service
public class AutorServiceImp implements AutorService {
    
    @Autowired
    private AutorRepository autorRepository;

    @Override
    public List<Autor> listarAutores() {
        return (List<Autor>) autorRepository.findAll();
    }

}
