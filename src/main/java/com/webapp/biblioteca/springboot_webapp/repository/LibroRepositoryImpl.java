package com.webapp.biblioteca.springboot_webapp.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.webapp.biblioteca.springboot_webapp.models.Libro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class LibroRepositoryImpl implements LibroRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Libro> buscarConFiltros(Integer year, String author, String editorial, String categoria, String nombre, Pageable pageable) {
        StringBuilder jpql = new StringBuilder("SELECT l FROM Libro l WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if (year != null) {
            jpql.append(" AND l.anio_publicacion = :year");
            params.put("year", year);
        }
        if (author != null && !author.isEmpty()) {
            jpql.append(" AND LOWER(l.autor.nombre) = LOWER(:author)");
            params.put("author", author);
        }
        if (editorial != null && !editorial.isEmpty()) {
            jpql.append(" AND LOWER(l.editorial) = LOWER(:editorial)");
            params.put("editorial", editorial);
        }
        if (categoria != null && !categoria.isEmpty()) {
            jpql.append(" AND LOWER(l.categoria.nombre_categoria) = LOWER(:categoria)");
            params.put("categoria", categoria);
        }
        if (nombre != null && !nombre.isEmpty()) {
            jpql.append(" AND LOWER(l.titulo) LIKE LOWER(CONCAT('%', :nombre, '%'))");
            params.put("nombre", nombre);
        }

        TypedQuery<Libro> query = entityManager.createQuery(jpql.toString(), Libro.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(jpql.toString().replace("SELECT l", "SELECT COUNT(l)"), Long.class);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
            countQuery.setParameter(entry.getKey(), entry.getValue());
        }

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Libro> libros = query.getResultList();
        Long total = countQuery.getSingleResult();

        return new PageImpl<>(libros, pageable, total);
    }
}
