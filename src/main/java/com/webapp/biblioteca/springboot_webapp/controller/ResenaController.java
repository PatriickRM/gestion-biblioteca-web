package com.webapp.biblioteca.springboot_webapp.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.webapp.biblioteca.springboot_webapp.models.Libro;
import com.webapp.biblioteca.springboot_webapp.models.Resena;
import com.webapp.biblioteca.springboot_webapp.models.Usuario;
import com.webapp.biblioteca.springboot_webapp.service.LibroService;
import com.webapp.biblioteca.springboot_webapp.service.ResenaService;
import com.webapp.biblioteca.springboot_webapp.service.UsuarioService;

@Controller
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LibroService libroService;

    @PostMapping("/guardar")
    public String guardarResena(@ModelAttribute Resena resena,
                                @RequestParam("idLibro") int idLibro,
                                Principal principal) {

        Usuario usuario = usuarioService.buscarPorUsername(principal.getName());
        Libro libro = libroService.buscarLibroPorId(idLibro);

        resena.setUsuario(usuario);
        resena.setLibro(libro);
        resena.setFecha(LocalDateTime.now());

        resenaService.guardarResena(resena);
        return "redirect:/libros/detalle/" + idLibro + "?resenaExitosa";
    }
}