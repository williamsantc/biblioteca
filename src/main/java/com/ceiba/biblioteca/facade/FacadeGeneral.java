/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.facade;

import com.ceiba.biblioteca.mediator.MediatorLibro;
import com.ceiba.biblioteca.mediator.MediatorPrestamo;
import com.ceiba.biblioteca.mediator.MediatorUsuario;
import com.ceiba.biblioteca.models.Libro;
import com.ceiba.biblioteca.models.Prestamo;
import com.ceiba.biblioteca.models.Usuario;
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

    private final MediatorLibro mediatorLibro = MediatorLibro.getInstance();
    
    private final MediatorPrestamo mediatorPrestamo = MediatorPrestamo.getInstance();
    
    private final MediatorUsuario mediatorUsuario = MediatorUsuario.getInstance();
    
    public boolean registerLibro(Libro libro) {
        return this.mediatorLibro.registerLibro(libro);
    }
    
    public Libro findLibro(String idLibro) {
        return this.mediatorLibro.findLibro(idLibro);
    }
    
    public List listLibros () {
        return this.mediatorLibro.listLibros();
    }
    
    public boolean deleteLibro(Integer librId) {
        return this.mediatorLibro.deleteLibro(librId);
    }
    
    public boolean registerPrestamo(Prestamo prestamo) {
        return this.mediatorPrestamo.registerPrestamo(prestamo);
    }
    
    public Usuario findUsuario (String usuaId) {
        return this.mediatorUsuario.findUsuario(usuaId);
    }

}
