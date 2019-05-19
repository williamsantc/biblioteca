/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.mediator;

import com.ceiba.biblioteca.logic.Methods;
import com.ceiba.biblioteca.models.Libro;
import com.ceiba.biblioteca.models.Prestamo;
import com.ceiba.biblioteca.util.EntityManagerUtil;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;

/**
 *
 * @author willi
 */
public class MediatorPrestamo {
    
    private static final MediatorPrestamo INSTANCE = new MediatorPrestamo();
    
    public static MediatorPrestamo getInstance() {
        return INSTANCE;
    }
    
    public boolean registerPrestamo(Prestamo prestamo) {
        boolean response = false;
        EntityManagerUtil.createFactory();
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        
        try {
            entityManager.getTransaction().begin();
            
            long currentDate = Calendar.getInstance().getTimeInMillis();
            prestamo.setPresFecha(new Date(currentDate));
            
            Libro libro = prestamo.getLibrId();
            
            if(Methods.esPalindromo(libro.getLibrIsbn())) {
                prestamo.setError("Los libros palíndromos solo se pueden utilizar en la biblioteca.");
                return false;
            }
            
            Date fechamaxima = null;
            
            if(Methods.sumaDigitosNumericos(libro.getLibrIsbn()) > 30) {
                Calendar calendarFechaMaxima = Calendar.getInstance();
                calendarFechaMaxima.setTimeInMillis(currentDate);
                
                // sumar 15 días
                calendarFechaMaxima.set(Calendar.DAY_OF_YEAR, calendarFechaMaxima.get(Calendar.DAY_OF_YEAR) + 15);
                
                // saltar domingo
                if(calendarFechaMaxima.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    calendarFechaMaxima.set(Calendar.DAY_OF_YEAR, calendarFechaMaxima.get(Calendar.DAY_OF_YEAR) + 1);
                }
                
                fechamaxima = new Date(calendarFechaMaxima.getTimeInMillis());
            }
            
            prestamo.setPresFechalimite(fechamaxima);
            
            entityManager.persist(prestamo);
            
            entityManager.getTransaction().commit();
            response = true;
        } catch (Exception e) {
            System.out.println("MediatorPrestamo :: registrarPrestamo :: " + e.getMessage());
            e.printStackTrace();
        } finally {
            entityManager.close();
            EntityManagerUtil.closeFactory();
        }
        
        return response;
    }
}
