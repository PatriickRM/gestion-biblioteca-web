package com.webapp.biblioteca.springboot_webapp.service;


import com.webapp.biblioteca.springboot_webapp.models.Usuario;

public interface AuthService {
    Usuario login(String usuario, String clavePlano);
    String encriptarClave(String clave);
}
