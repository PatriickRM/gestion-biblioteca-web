package com.webapp.biblioteca.springboot_webapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private int idLibro;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    private String editorial;
    private int anio_publicacion;
    private String isbn;
    private String imagen;
    private String estado;

    

    public Libro() {
    }

    public Libro(int idlibro, String titulo, Autor autor, Categoria categoria, String editorial, int anio_publicacion, String isbn, String imagen,String estado) {
        this.idLibro = idlibro;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.editorial = editorial;
        this.anio_publicacion = anio_publicacion;
        this.isbn = isbn;
        this.imagen = imagen;
        this.estado = estado;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio_publicacion() {
        return anio_publicacion;
    }

    public void setAnio_publicacion(int anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
