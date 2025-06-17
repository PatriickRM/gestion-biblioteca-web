package com.webapp.biblioteca.springboot_webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.biblioteca.springboot_webapp.service.AuthServiceImp;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthServiceImp authService;

    public AuthController(AuthServiceImp authService) {
        this.authService = authService;
    }

    public record LoginRequest(String usuario, String clave) {}
    public record LoginResponse(int idtrabajador, String nombre, String cargo, String message) {}
    public record ErrorResponse(String error, String message) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // Validar que los campos no estén vacíos
            if (request.usuario() == null || request.usuario().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ErrorResponse("VALIDATION_ERROR", "El usuario es requerido"));
            }
            
            if (request.clave() == null || request.clave().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ErrorResponse("VALIDATION_ERROR", "La clave es requerida"));
            }

            var trabajador = authService.login(request.usuario().trim(), request.clave());
            
            LoginResponse response = new LoginResponse(
                trabajador.getIdTrabajador(),
                trabajador.getNombres(),
                trabajador.getCargo().getNombre_cargo(),
                "Login exitoso"
            );
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            System.err.println("Error en login: " + e.getMessage()); 
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("UNAUTHORIZED", e.getMessage()));
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("INTERNAL_ERROR", "Error interno del servidor"));
        }
    }
}
