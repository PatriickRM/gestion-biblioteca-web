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
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new CustomUserDetails(usuario); 
}
}