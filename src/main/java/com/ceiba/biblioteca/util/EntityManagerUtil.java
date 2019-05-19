/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author William Santos
 */
public class EntityManagerUtil {

    private static EntityManagerFactory FACTORY = null;

    public static void createFactory() {
        FACTORY = Persistence.createEntityManagerFactory("com.ceiba.biblioteca");
    }

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void closeFactory() {
        FACTORY.close();
    }

}
