package com.webapp.biblioteca.springboot_webapp.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webapp.biblioteca.springboot_webapp.models.DetallePrestamo;
import com.webapp.biblioteca.springboot_webapp.models.Libro;
import com.webapp.biblioteca.springboot_webapp.models.Prestamo;
import com.webapp.biblioteca.springboot_webapp.reports.IReportePrestamo;
import com.webapp.biblioteca.springboot_webapp.repository.LibroRepository;
import com.webapp.biblioteca.springboot_webapp.repository.PrestamoRepository;
import com.webapp.biblioteca.springboot_webapp.repository.UsuarioRepository;
import com.webapp.biblioteca.springboot_webapp.service.LibroService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LibroService libroService;
    @Autowired
    private IReportePrestamo reportePrestamo;

    

    @GetMapping("/nuevo")
    public String nuevoPrestamo(Model model, HttpSession session) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("libros", libroRepository.findByEstado("Disponible"));


        List<Libro> carrito = (List<Libro>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }
        model.addAttribute("carrito", carrito);

        // Mantener el ID del usuario seleccionado en la sesión
        Integer idUsuarioSeleccionado = (Integer) session.getAttribute("idUsuarioSeleccionado");
        model.addAttribute("idUsuarioSeleccionado", idUsuarioSeleccionado);
        return "form_prestamo";
    }

    @PostMapping("/agregarLibro")
    public String agregarLibro(@RequestParam("idLibro") int idLibro,@RequestParam(value = "idUsuario", required = false) Integer idUsuario, RedirectAttributes redirectAttributes,
                            HttpSession session) {
        // Agregar libro al carrito
        List<Libro> carrito = (List<Libro>) session.getAttribute("carrito");
        if (carrito == null) carrito = new ArrayList<>();
        carrito.add(libroService.buscarLibroPorId(idLibro));
        session.setAttribute("carrito", carrito);

        // Si no se recibió idUsuario, redirige sin error
        if (idUsuario != null) {
            session.setAttribute("idUsuarioSeleccionado", idUsuario);
        }

        return "redirect:/prestamos/nuevo";
    }

    @PostMapping("/guardar")
    public String guardarPrestamo(@RequestParam int idUsuario,  @RequestParam String fechaDevolucion,  @RequestParam(required = false) String comentario,
                              HttpSession session) {

        List<Libro> carrito = (List<Libro>) session.getAttribute("carrito");
        if (carrito == null || carrito.isEmpty()) {
            return "redirect:/prestamos/nuevo"; 
        }
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuarioRepository.findById(idUsuario).orElse(null));
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.parse(fechaDevolucion));
        prestamo.setEstado("Prestado");
        prestamo.setComentario(comentario);

        for (Libro libro : carrito) {
            libro.setEstado("Prestado");
            libroRepository.save(libro); // Actualizar el estado del libro

            DetallePrestamo detalle = new DetallePrestamo();
            detalle.setLibro(libro);
            detalle.setPrestamo(prestamo);
            prestamo.getDetalles().add(detalle);
        }
        prestamoRepository.save(prestamo);
        // Limpiar el carrito de la sesión
        session.removeAttribute("carrito");

        return "redirect:/prestamos/lista";
    }

    @PostMapping("/eliminarLibro")
    public String eliminarLibro(@RequestParam("idLibro") int libroId,
                            @RequestParam(value = "idUsuario", required = false) Integer idUsuario,
                            HttpSession session) {
        
        // Eliminar libro del carrito
        List<Libro> carrito = (List<Libro>) session.getAttribute("carrito");
        
        if (carrito != null) {
            carrito.removeIf(libro -> libro.getIdLibro() == libroId);
            session.setAttribute("carrito", carrito);
        }
        
        // Mantener usuario seleccionado si se envió
        if (idUsuario != null) {
            session.setAttribute("idUsuarioSeleccionado", idUsuario);
        }
        
        return "redirect:/prestamos/nuevo";
    }

    @GetMapping("/lista")
    public String listarPrestamos(Model model) {
        model.addAttribute("prestamos", prestamoRepository.findAll());
        return "listaPrestamos";
    }
    
    @PostMapping("/devolver/{id}")
    public String registrarDevolucion(@PathVariable int id) {
        Prestamo prestamo = prestamoRepository.findById(id).orElse(null);

        if (prestamo != null) {
            prestamo.setEstado("Entregado");
            prestamo.setFechaEntrega(LocalDate.now());

            if (prestamo.getDetalles() != null) {
                for (DetallePrestamo detalle : prestamo.getDetalles()) {
                    Libro libro = detalle.getLibro();
                    if (libro != null) {
                        libro.setEstado("Disponible");
                        libroRepository.save(libro); // Actualizar el estado del libro
                    }
                }
            }
            prestamoRepository.save(prestamo); // Guardar el préstamo actualizado
        }
        return "redirect:/prestamos/lista";
    }
    @GetMapping("/detalle/{id}/libros")
    @ResponseBody
    public List<Map<String, Object>> getLibrosDetalle(@PathVariable int id) {
        Prestamo prestamo = prestamoRepository.findByIdPrestamo(id);
        List<Map<String, Object>> librosData = new ArrayList<>();
        
        for (DetallePrestamo detalle : prestamo.getDetalles()) {
            Map<String, Object> libroData = new HashMap<>();
            Libro libro = detalle.getLibro();
            
            libroData.put("titulo", libro.getTitulo());
            libroData.put("editorial", libro.getEditorial());
            libroData.put("anio_publicacion", libro.getAnio_publicacion());
            libroData.put("isbn", libro.getIsbn());
            libroData.put("imagen", libro.getImagen());
            libroData.put("estado", libro.getEstado());
            
            // Datos del autor
            Map<String, Object> autorData = new HashMap<>();
            autorData.put("nombre", libro.getAutor().getNombre());
            libroData.put("autor", autorData);
            
            // Datos de la categoría
            Map<String, Object> categoriaData = new HashMap<>();
            categoriaData.put("nombre", libro.getCategoria().getNombre_categoria()); 
            libroData.put("categoria", categoriaData);
            
            librosData.add(libroData);
        }
        
        return librosData;
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id){
        prestamoRepository.deleteById(id);
        return "redirect:/prestamos/lista";
    }

    @GetMapping("/reportes")	
  	public void createPdf(HttpServletResponse response) throws JRException, IOException {
	 response.setContentType("application/pdf"); DateFormat dateforma=new
		 SimpleDateFormat("yyyy-MM-dd:hh:mm:ss"); String
		 currenDateTime=dateforma.format(new Date()); String
		 headerKey="Content-Disposition"; String
		 headerValue="attachment;filename=pdf_"+currenDateTime+".pdf";
		 response.setHeader(headerKey,headerValue);
		 reportePrestamo.exportJapertReport(response);
		
	} 

}