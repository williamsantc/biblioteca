/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.util;


/**
 *
 * @author William
 */
public class BadRequestException extends Exception {

    /**
     * Creates a new instance of <code>BadRequest</code> without detail message.
     */
    public BadRequestException() {
    }

    /**
     * Constructs an instance of <code>BadRequest</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public BadRequestException(String msg) {
        super(msg);
    }
    
    public BadRequestException(Throwable cause) {
        super(cause);
    }
    
    public BadRequestException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
