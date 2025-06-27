package com.webapp.biblioteca.springboot_webapp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.biblioteca.springboot_webapp.models.Libro;
import com.webapp.biblioteca.springboot_webapp.service.AutorService;
import com.webapp.biblioteca.springboot_webapp.service.CategoriaService;
import com.webapp.biblioteca.springboot_webapp.service.LibroService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private AutorService autorService;
    
    @GetMapping("/listar")
    public String listarLibros(Model model) {
        List<Libro> libros = libroService.listarLibros();
        model.addAttribute("libros", libros);
        return "libros"; 
    }
    //Registrar libro
    @GetMapping("/nuevo")
    public String mostrarFormRegistro(Model model) {
        Libro libro = new Libro();
        model.addAttribute("libro", libro);
         model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("listaAutores", autorService.listarAutores());
        model.addAttribute("formAction", "/libros/registrar");
        return "libros_form"; 
    }
    @PostMapping("/registrar")
    public String registrarLibro(@ModelAttribute Libro libro,@RequestParam("imagenFile") MultipartFile imagenFile,
                             Model model) {
    try {
        if (!imagenFile.isEmpty()) {
            // Nombre original del archivo
            String nombreArchivo = imagenFile.getOriginalFilename();
            String ruta = new ClassPathResource("static/images").getFile().getAbsolutePath();

            Path path = Paths.get(ruta, nombreArchivo);
            Files.write(path, imagenFile.getBytes());

            
            libro.setImagen(nombreArchivo);
        } else {
            libro.setImagen("default.png"); // por si no suben imagen
        }

        libroService.registrarLibro(libro);
        return "redirect:/libros";
    } catch (IOException e) {
        e.printStackTrace();
        model.addAttribute("error", "Error al subir la imagen");
        return "libros_form";
    }
}

    //Editar libro
    @GetMapping("/editar/{id}")
    public String mostrarFormEditar(@PathVariable Integer id, Model model) {
        Libro libro = libroService.buscarLibroPorId(id);
        if (libro == null) {
            return "redirect:/libros/listar"; 
        }
        model.addAttribute("libro", libro);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("listaAutores", autorService.listarAutores());
        model.addAttribute("formAction", "/libros/registrar");
        return "libros_form"; 
    }

    @PostMapping("/editar")
    public String editarLibro(Libro libro) {
        libroService.actualizarLibro(libro);
        return "redirect:/libros/listar"; 
    }

    //Eliminar libro
    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Integer id) {
        Libro libro = libroService.buscarLibroPorId(id);
        if (libro != null) {
            libroService.eliminarLibro(libro);
        }
        return "redirect:/libros/listar"; 
    }

    // Obtener libro por ID
    @GetMapping("/detalles/{id}")
    public String verDetallesLibro(@PathVariable Integer id, Model model) {
        Libro libro = libroService.buscarLibroPorId(id);
        if (libro == null) {
            return "redirect:/libros/listar"; 
        }
        model.addAttribute("libro", libro);
        return "libro_detalles"; 
    }
    @GetMapping("")
    public String redirigirALibros() {
        return "redirect:/libros/listar";
    }
    // Obtener libro por ID con JSON
    @GetMapping("/detalles-json-libro/{id}")
    @ResponseBody
    public Libro obtenerDetallesLibro(@PathVariable("id") Integer id) {
        return libroService.buscarLibroPorId(id);
    }
}