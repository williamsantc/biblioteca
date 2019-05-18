/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.mediator;

import com.ceiba.biblioteca.models.Libro;
import com.ceiba.biblioteca.util.EntityManagerUtil;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author willi
 */
public class MediatorLibro {

    private static final MediatorLibro INSTANCE = new MediatorLibro();

    public static MediatorLibro getInstance() {
        return INSTANCE;
    }

    public boolean registerLibro(Libro libro) {

        boolean response = false;
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Libro> listConMismoISBN = entityManager.createNamedQuery("Libro.findByLibrIsbn").setParameter("librIsbn", libro.getLibrIsbn()).getResultList();

            if (listConMismoISBN != null && listConMismoISBN.size() > 0) {
                Libro libroEncontrado = listConMismoISBN.get(0);
                if (listConMismoISBN.get(0).getLibrNombre().equalsIgnoreCase(libro.getLibrNombre())) {
                    libro.setError("El ISBN ingresado ya pertenece a un libro");
                    return false;
                }
                libroEncontrado.setLibrCantejemplares(libroEncontrado.getLibrCantejemplares() + 1);
            } else {
                entityManager.persist(libro);
            }
            
            entityManager.getTransaction().commit();
            entityManager.close();
            response = true;
        } catch (Exception e) {
            System.out.println("ControllerLibro :: registerLibro :: " + e.getMessage());
            entityManager.close();
        }
        return response;
    }

    public Libro findLibro(String idLibro) {
        Libro libro = null;
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            libro = entityManager.find(Libro.class, libro.getLibrId());
        } catch (Exception e) {
            System.out.println("ControllerLibro :: findLibro :: " + e.getMessage());
            entityManager.close();
        }
        return libro;
    }

    public List<Libro> listLibros() {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        List<Libro> lista = null;
        try {
            lista = entityManager.createNamedQuery("Libro.findAll").getResultList();
        } catch (Exception e) {
            System.out.println("ControllerLibro :: listLibros :: " + e.getMessage());
            entityManager.close();
        }
        return lista;
    }
}
