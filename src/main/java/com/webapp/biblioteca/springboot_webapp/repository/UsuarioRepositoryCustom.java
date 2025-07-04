package com.webapp.biblioteca.springboot_webapp.repository;

import java.util.List;

import com.webapp.biblioteca.springboot_webapp.models.Usuario;

public interface UsuarioRepositoryCustom {
	List<Usuario> buscarporRol(String rol); //Consulta para buscar los usuario por rol
}