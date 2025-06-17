package com.webapp.biblioteca.springboot_webapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargos")
public class Cargos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private int id_cargo;
    @Column(name = "nombre_cargo")
    private String nombre_cargo;

    public Cargos() {
    }
    public Cargos(int id_cargo, String nombre_cargo) {
        this.id_cargo = id_cargo;
        this.nombre_cargo = nombre_cargo;
    }
    public int getId_cargo() {
        return id_cargo;
    }
    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }
    public String getNombre_cargo() {
        return nombre_cargo;
    }
    public void setNombre_cargo(String nombre_cargo) {
        this.nombre_cargo = nombre_cargo;
    }
    @Override
    public String toString() {
        return "Cargos [id_cargo=" + id_cargo + ", nombre_cargo=" + nombre_cargo + "]";
    }
}
