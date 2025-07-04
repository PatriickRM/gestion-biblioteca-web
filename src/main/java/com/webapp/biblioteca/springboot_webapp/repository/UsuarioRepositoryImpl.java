package com.webapp.biblioteca.springboot_webapp.repository;

import java.util.List;

import com.webapp.biblioteca.springboot_webapp.models.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom{
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Usuario> buscarporRol(String rol) {
		String jpql = "SELECT u FROM Usuario u WHERE LOWER(u.rol.nombreRol) = LOWER(:rol)";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("rol", rol);
        return query.getResultList();
	}

}
