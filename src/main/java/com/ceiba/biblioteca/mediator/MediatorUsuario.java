/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.mediator;

import com.ceiba.biblioteca.models.Libro;
import com.ceiba.biblioteca.models.Usuario;
import com.ceiba.biblioteca.util.EntityManagerUtil;
import javax.persistence.EntityManager;

/**
 *
 * @author willi
 */
public class MediatorUsuario {
    
    private static final MediatorUsuario INSTANCE = new MediatorUsuario();
    
    public static MediatorUsuario getInstance() {
        return INSTANCE;
    }
    
    public Usuario findUsuario(String usuaId) {
        Usuario usuario = null;
        EntityManagerUtil.createFactory();
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            usuario = entityManager.find(Usuario.class, usuaId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("MediatorUsuario :: findUsuario :: " + e.getMessage());
            e.printStackTrace();
        } finally {
            entityManager.close();
            EntityManagerUtil.closeFactory();
        }
        return usuario;
    }
}
