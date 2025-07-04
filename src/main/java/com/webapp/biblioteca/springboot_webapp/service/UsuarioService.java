package com.webapp.biblioteca.springboot_webapp.service;

import java.util.List;

import com.webapp.biblioteca.springboot_webapp.models.Usuario;

public interface UsuarioService {
    // Métodos
    void RegistrarUsuario(Usuario usuario);
    void ActualizarUsuario(Usuario usuario);
    void EliminarUsuario(Usuario usuario);
    Usuario BuscarUsuarioPorId(Integer id);
    List<Usuario> ListarUsuarios();
    Usuario buscarPorUsername(String username);
    List<Usuario> buscarporRol(String rol); // Buscar usuarios solo por rol
    long contarUsuarios();
}
