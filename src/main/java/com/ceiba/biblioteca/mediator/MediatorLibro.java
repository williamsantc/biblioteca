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
        EntityManagerUtil.createFactory();
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<Libro> libroConMismoISBN = entityManager.createNamedQuery("Libro.findByLibrIsbn").setParameter("librIsbn", libro.getLibrIsbn()).getResultList();

            if (libroConMismoISBN != null && libroConMismoISBN.size() > 0) {
                Libro libroFound = libroConMismoISBN.get(0);
                if (!libroFound.getLibrNombre().equalsIgnoreCase(libro.getLibrNombre())) {
                    libro.setError("El ISBN ingresado ya pertenece a un libro");
                    return false;
                }
                libroFound.setLibrCantejemplares(libroFound.getLibrCantejemplares() + 1);
            } else {
                entityManager.persist(libro);
            }
            
            entityManager.getTransaction().commit();
            response = true;
        } catch (Exception e) {
            System.out.println("MediatorLibro :: registerLibro :: " + e.getMessage());
            e.printStackTrace();
        } finally {
            entityManager.close();
            EntityManagerUtil.closeFactory();
        }
        
        return response;
    }

    public Libro findLibro(String librId) {
        Libro libro = null;
        EntityManagerUtil.createFactory();
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            libro = entityManager.find(Libro.class, librId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("MediatorLibro :: findLibro :: " + e.getMessage());
            e.printStackTrace();
        } finally {
            entityManager.close();
            EntityManagerUtil.closeFactory();
        }
        return libro;
    }

    public List<Libro> listLibros() {
        EntityManagerUtil.createFactory();
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        List<Libro> lista = null;
        try {
            entityManager.getTransaction().begin();
            lista = entityManager.createNamedQuery("Libro.findAll").getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("MediatorLibro :: listLibros :: " + e.getMessage());
            e.printStackTrace();
        } finally {
            entityManager.close();
            EntityManagerUtil.closeFactory();
        }
        return lista;
    }
    
    public boolean deleteLibro(Integer librId) {
        EntityManagerUtil.createFactory();
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            int result = entityManager.createNamedQuery("Libro.deleteByLibrId").setParameter("librId", librId).executeUpdate();
            System.out.println(result);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("MediatorLibro :: deleteLibro :: " + e.getMessage());
            e.printStackTrace();
        } finally {
            entityManager.close();
            EntityManagerUtil.closeFactory();
        }
        return true;
    }
}
