package com.webapp.biblioteca.springboot_webapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "autor")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_autor;
    @Column(nullable = false, length = 100)
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getId_autor() {
        return id_autor;
    }
    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }
}
