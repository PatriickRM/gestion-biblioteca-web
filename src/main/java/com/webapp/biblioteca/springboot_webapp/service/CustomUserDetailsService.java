package com.webapp.biblioteca.springboot_webapp.service;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.webapp.biblioteca.springboot_webapp.models.Usuario;
import com.webapp.biblioteca.springboot_webapp.repository.AuthRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepository repository;

    public CustomUserDetailsService(AuthRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Intentando cargar usuario: " + username);

        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("Usuario no encontrado: " + username);
                    return new UsernameNotFoundException("Usuario no encontrado: " + username);
                });

        System.out.println("Usuario encontrado: " + usuario.getUsername());
        System.out.println("Password hash: " + usuario.getPassword());
        System.out.println("Asignando rol a usuario: " + usuario.getRol().getNombreRol());

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRol().getNombreRol().toUpperCase())
                .build();

                

    }
}