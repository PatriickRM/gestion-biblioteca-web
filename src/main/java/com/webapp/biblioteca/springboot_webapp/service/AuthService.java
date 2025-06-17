package com.webapp.biblioteca.springboot_webapp.service;

import com.webapp.biblioteca.springboot_webapp.models.Trabajador;

public interface AuthService {
    Trabajador login(String correo, String clavePlano);
    String encriptarClave(String clave);
}
