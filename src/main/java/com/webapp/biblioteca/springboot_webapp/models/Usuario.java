package com.webapp.biblioteca.springboot_webapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;
    @Column(name = "nombres")
    private String nombre;
    @Column(name = "apellidos")
    private String apellido;
    @Column(name = "dni")
    private String dniuser;
    @Column(name = "correo")
    private String correouser;
    @Column(name = "telefono")
    private String telefonouser;
    @Column(name = "tipo_usuario")
    private String tipo_user;
    


    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre, String apellido, String dniuser, String correouser, String telefonouser, String tipo_user) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dniuser = dniuser;
        this.correouser = correouser;
        this.telefonouser = telefonouser;
        this.tipo_user = tipo_user;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDniuser() {
        return dniuser;
    }

    public void setDniuser(String dniuser) {
        this.dniuser = dniuser;
    }

    public String getCorreouser() {
        return correouser;
    }

    public void setCorreouser(String correouser) {
        this.correouser = correouser;
    }

    public String getTelefonouser() {
        return telefonouser;
    }

    public void setTelefonouser(String telefonouser) {
        this.telefonouser = telefonouser;
    }

    public String getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(String tipo_user) {
        this.tipo_user = tipo_user;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   @Override
    public String toString() {
        return "Usuario [id_usuario=" + id_usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", dniuser="
                + dniuser + ", correouser=" + correouser + ", telefonouser=" + telefonouser + ", tipo_user=" + tipo_user
                + "]";
    }
    
}
