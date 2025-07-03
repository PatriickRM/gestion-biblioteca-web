package com.webapp.biblioteca.springboot_webapp.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.biblioteca.springboot_webapp.models.Libro;
import com.webapp.biblioteca.springboot_webapp.models.Usuario;
import com.webapp.biblioteca.springboot_webapp.service.CustomUserDetails;
import com.webapp.biblioteca.springboot_webapp.service.LibroService;
import com.webapp.biblioteca.springboot_webapp.service.ResenaService;
import com.webapp.biblioteca.springboot_webapp.service.UsuarioService;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService; 
    @Autowired
    private LibroService libroService;
    @Autowired
    private ResenaService resenaService;


    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
   public String mostrarHome(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(required = false) Integer year,
    @RequestParam(required = false) String author,
    @RequestParam(required = false) String editorial,
    @RequestParam(required = false) String category,
    @RequestParam(required = false) String name,
    Model model, Principal principal
) {
    Usuario usuarioAutenticado = usuarioService.buscarPorUsername(principal.getName());
    model.addAttribute("usuario", usuarioAutenticado);

    Pageable pageable = PageRequest.of(page, 8);
    Page<Libro> libros = libroService.buscarConFiltros(year, author, editorial, category, name, pageable);
    model.addAttribute("libros", libros.getContent());
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", libros.getTotalPages());

    // filtros seleccionados
    model.addAttribute("year", year);
    model.addAttribute("author", author);
    model.addAttribute("editorial", editorial);
    model.addAttribute("category", category);
    model.addAttribute("name", name);

    // datos para los filtros
    model.addAttribute("anios", libroService.obtenerAniosPublicacion());
    model.addAttribute("autores", libroService.obtenerAutores());
    model.addAttribute("editoriales", libroService.obtenerEditoriales());
    model.addAttribute("categorias", libroService.obtenerCategorias());

    // promedio de reseñas
    Map<Integer, Double> promedios = new HashMap<>();
    for (Libro libro : libros.getContent()) {
        Double promedio = resenaService.promedioCalificacionPorLibro(libro.getIdLibro());
        promedios.put(libro.getIdLibro(), promedio);
    }
    model.addAttribute("promedios", promedios);

    return "home";
}

    @ModelAttribute("usuarioActual")
    public Usuario usuarioActual(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails customUser) {
                return customUser.getUsuario();
            }
        }
        return null;
    }

    @GetMapping("/preguntas")
    public String preguntas(Model model) {
        model.addAttribute("extraClass", "faq-container");
        return "preguntas";
    }

    @GetMapping("/condiciones")
    public String condiciones(Model model) {
        model.addAttribute("extraClass", "faq-container");
        return "condiciones";
    }

 }
