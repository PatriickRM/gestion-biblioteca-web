package com.webapp.biblioteca.springboot_webapp.controller;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.webapp.biblioteca.springboot_webapp.models.Prestamo;
import com.webapp.biblioteca.springboot_webapp.service.CustomUserDetails;
import com.webapp.biblioteca.springboot_webapp.service.PrestamoServiceImp;

@Controller
public class MisPrestamosController {

    @Autowired
    private PrestamoServiceImp prestamoService;

    @GetMapping("/mis-prestamos")
    public String misPrestamos(Model model, Authentication authentication) {
        try {
            model.addAttribute("extraClass", "faq-container");
            // Obtener el usuario autenticado
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            int idUsuario = userDetails.getIdUsuario();
            
            // Obtener préstamos del usuario
            List<Prestamo> prestamos = prestamoService.obtenerPrestamosPorUsuario(idUsuario);
            
            // Clasificar préstamos por estado
            List<Prestamo> prestamosActivos = prestamos.stream()
                .filter(p -> "Prestado".equals(p.getEstado()))
                .collect(Collectors.toList());
            
            List<Prestamo> prestamosEntregados = prestamos.stream()
                .filter(p -> "Entregado".equals(p.getEstado()))
                .collect(Collectors.toList());
            
            // Calcular días restantes para préstamos activos
            LocalDate hoy = LocalDate.now();
            Map<Integer, Long> diasRestantes = new HashMap<>();
            
            for (Prestamo prestamo : prestamosActivos) {
                if (prestamo.getFechaDevolucion() != null) {
                    long dias = ChronoUnit.DAYS.between(hoy, prestamo.getFechaDevolucion());
                    diasRestantes.put(prestamo.getIdPrestamo(), dias);
                }
            }
            
            model.addAttribute("prestamosActivos", prestamosActivos);
            model.addAttribute("prestamosEntregados", prestamosEntregados);
            model.addAttribute("diasRestantes", diasRestantes);
            model.addAttribute("totalPrestamos", prestamos.size());
            model.addAttribute("totalActivos", prestamosActivos.size());
            model.addAttribute("totalEntregados", prestamosEntregados.size());
            
            return "mis-prestamos";
            
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar los préstamos");
            return "mis-prestamos";
        }
    }
}