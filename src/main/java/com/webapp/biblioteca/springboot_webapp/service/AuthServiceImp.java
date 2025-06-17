package com.webapp.biblioteca.springboot_webapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webapp.biblioteca.springboot_webapp.models.Trabajador;
import com.webapp.biblioteca.springboot_webapp.repository.AuthRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AuthServiceImp implements AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImp(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Trabajador login(String usuario, String clavePlano) {
        Trabajador trabajador = authRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no registrado"));

        if (!passwordEncoder.matches(clavePlano, trabajador.getPassword())) {
            throw new RuntimeException("Clave incorrecta");
        }

        return trabajador;
    }

    @Override
    public String encriptarClave(String clave) {
        return passwordEncoder.encode(clave);
    }
    @PostConstruct
    public void generarHash() {
    String hash = passwordEncoder.encode("123456");
    System.out.println("HASH para 123456 → " + hash);
    }
}