package com.webapp.biblioteca.springboot_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webapp.biblioteca.springboot_webapp.models.Categoria;
import com.webapp.biblioteca.springboot_webapp.service.CategoriaService;


@Controller
@RequestMapping("/configuracion")
public class ConfiguracionController {

	  @Autowired
	    private CategoriaService categoriaService;
	  	// Método 1: Mostrar formulario + lista
	    @GetMapping
	    public String vistaCategorias(Model model) {
	        model.addAttribute("categoria", new Categoria()); // Para el formulario
	        model.addAttribute("categorias", categoriaService.listarCategorias()); // Para mostrar la lista
	        return "configuracion";
	    }

	    // Método 2: Guardar nueva categoría
	    @PostMapping("/guardar")
	    public String guardarCategoria(@ModelAttribute Categoria categoria) {
	        categoriaService.registrarCategoria(categoria);
	        return "redirect:/configuracion";
	    }
}
