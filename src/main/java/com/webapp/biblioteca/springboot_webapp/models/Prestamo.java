package com.webapp.biblioteca.springboot_webapp.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrestamo;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private LocalDate fechaEntrega;
    private String estado;
    private String comentario;

    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL)
    private List<DetallePrestamo> detalles = new ArrayList<>();

    public Prestamo() {
    }

    public Prestamo(int idPrestamo, Usuario usuario, LocalDate fechaPrestamo, LocalDate fechaDevolucion, LocalDate fechaEntrega, String estado, String comentario) {
        this.idPrestamo = idPrestamo;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.comentario = comentario;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<DetallePrestamo> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePrestamo> detalles) {
        this.detalles = detalles;
    }
    public String getFechaFormateada() {
        return fechaDevolucion != null ? fechaDevolucion.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "";
    }

    
    
}