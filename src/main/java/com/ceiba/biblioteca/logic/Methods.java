/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.logic;

/**
 *
 * @author ACER
 */
public class Methods {

    public static int sumaDigitosNumericos(String c) {
        int acum = 0;
        for (int i = 0; i < c.length(); i++) {
            if (c.charAt(i) >= 48 && c.charAt(i) <= 57) {
                acum = acum + (c.charAt(i) - 48);
            }
        }
        return acum;
    }

    public static boolean esPalindromo(String c) {
        c = c.toUpperCase();
        int j = c.length() - 1;
        for (int i = 0; i < c.length() / 2; i++) {
            if (c.charAt(i) != c.charAt(j - i)) {
                return false;
            }
        }
        return true;
    }

}
