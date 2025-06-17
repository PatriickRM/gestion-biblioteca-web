package com.webapp.biblioteca.springboot_webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.webapp.biblioteca.springboot_webapp.models.Trabajador;
import com.webapp.biblioteca.springboot_webapp.repository.AuthRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Intentando cargar usuario: " + username); 
        
        Trabajador trabajador = repository.findByUsuario(username)
            .orElseThrow(() -> {
                System.out.println("Usuario no encontrado: " + username); 
                return new UsernameNotFoundException("Usuario no encontrado: " + username);
            });

        System.out.println("Usuario encontrado: " + trabajador.getUsuario()); 
        System.out.println("Password hash: " + trabajador.getPassword().substring(0, 10) + "..."); // Debug (solo primeros caracteres)

        return User.builder()
                .username(trabajador.getUsuario())
                .password(trabajador.getPassword()) 
                .roles("ADMIN") 
                .build();
    }
}
