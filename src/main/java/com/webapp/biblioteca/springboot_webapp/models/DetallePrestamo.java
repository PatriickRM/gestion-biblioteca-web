package com.webapp.biblioteca.springboot_webapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_prestamo")
public class DetallePrestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle;
    @ManyToOne
    @JoinColumn(name = "id_prestamo")
    private Prestamo prestamo;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;

    public DetallePrestamo() {
    
    }

    public DetallePrestamo(Prestamo prestamo, Libro libro) {
        this.prestamo = prestamo;
        this.libro = libro;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    
}