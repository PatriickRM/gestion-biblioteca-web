package com.webapp.biblioteca.springboot_webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.biblioteca.springboot_webapp.models.Roles;
import com.webapp.biblioteca.springboot_webapp.repository.RolRepository;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Roles> listarRoles() {
        return rolRepository.findAll();
    }

    public Roles findByNombreRol(String nombreRol) {
        return rolRepository.findByNombreRol(nombreRol);
    }
}