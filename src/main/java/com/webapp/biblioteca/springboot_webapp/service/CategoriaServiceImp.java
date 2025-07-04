package com.webapp.biblioteca.springboot_webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.biblioteca.springboot_webapp.models.Categoria;
import com.webapp.biblioteca.springboot_webapp.repository.CategoriaRepository;

@Service
public class CategoriaServiceImp implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarCategorias() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

	@Override
	public void registrarCategoria(Categoria categoria) {
		categoriaRepository.save(categoria);
		
	}

}
