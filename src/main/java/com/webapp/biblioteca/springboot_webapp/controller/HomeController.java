package com.webapp.biblioteca.springboot_webapp.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.biblioteca.springboot_webapp.models.Libro;
import com.webapp.biblioteca.springboot_webapp.models.Usuario;
import com.webapp.biblioteca.springboot_webapp.service.LibroService;
import com.webapp.biblioteca.springboot_webapp.service.UsuarioService;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService; 
    @Autowired
    private LibroService libroService;

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String mostrarHome(@RequestParam(defaultValue = "0") int page, Model model, Principal principal) {
        Usuario usuarioAutenticado = usuarioService.buscarPorUsername(principal.getName());
        model.addAttribute("usuario", usuarioAutenticado);
        
        Pageable pageable = PageRequest.of(page, 10);
        Page<Libro> libros = libroService.listarLibrosPaginados(pageable);

        model.addAttribute("libros", libros.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", libros.getTotalPages());
        return "home";
    }
}
