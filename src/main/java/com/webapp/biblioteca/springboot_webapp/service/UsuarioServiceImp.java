package com.webapp.biblioteca.springboot_webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.biblioteca.springboot_webapp.models.Usuario;
import com.webapp.biblioteca.springboot_webapp.repository.UsuarioRepository;

@Service
public class UsuarioServiceImp implements UsuarioService {
    //Aplicar inyeccion de dependencias
    @Autowired
    private UsuarioRepository usuarioRepository;
    

    @Override
    public void RegistrarUsuario(Usuario usuario) {
       usuarioRepository.save(usuario);
    }

    @Override
    public void ActualizarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void EliminarUsuario(Usuario usuario) {
        usuarioRepository.deleteById(usuario.getIdUsuario());
    }

    @Override
    public Usuario BuscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> ListarUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    

}
