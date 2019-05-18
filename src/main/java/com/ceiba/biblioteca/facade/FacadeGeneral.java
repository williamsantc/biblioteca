/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.facade;

import com.ceiba.biblioteca.mediator.MediatorLibro;
import com.ceiba.biblioteca.models.Libro;
import java.util.List;

/**
 *
 * @author willi
 */
public class FacadeGeneral {
    
    private static final FacadeGeneral INSTANCE = new FacadeGeneral();

    public static FacadeGeneral getInstance() {
        return INSTANCE;
    }

    private final MediatorLibro mediatorLibro = new MediatorLibro();
    
    public boolean registerLibro(Libro libro) {
        return this.mediatorLibro.registerLibro(libro);
    }
    
    public List<Libro> listLibros () {
        return this.mediatorLibro.listLibros();
    }

}
