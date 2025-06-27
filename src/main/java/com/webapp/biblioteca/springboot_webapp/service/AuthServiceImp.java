package com.webapp.biblioteca.springboot_webapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webapp.biblioteca.springboot_webapp.models.Usuario;
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
    public Usuario login(String usuario, String clavePlano) {
        Usuario user = authRepository.findByUsername(usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no registrado"));

        if (!passwordEncoder.matches(clavePlano, user.getPassword())) {
            throw new RuntimeException("Clave incorrecta");
        }

        return user;
    }

    @Override
    public String encriptarClave(String clave) {
        return passwordEncoder.encode(clave);
    }

    // Para depurar y probar
    @PostConstruct
    public void generarHash() {
        String hash = passwordEncoder.encode("admin123");
        System.out.println("HASH para admin123 → " + hash);
    }
}