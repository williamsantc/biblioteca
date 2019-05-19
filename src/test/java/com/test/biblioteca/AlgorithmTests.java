/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.biblioteca;

import com.ceiba.biblioteca.logic.Methods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author willi
 */
@DisplayName("Test de algoritmos de usados en la aplicación")
public class AlgorithmTests {
    
    @Test
    @DisplayName("Test de palabras palindromas")
    void testPalindromo() {
        Assertions.assertTrue(Methods.esPalindromo("reconocer"));
        Assertions.assertTrue(Methods.esPalindromo("rayar"));
        Assertions.assertTrue(Methods.esPalindromo("e"));
        Assertions.assertTrue(Methods.esPalindromo("a11a"));
        Assertions.assertTrue(Methods.esPalindromo("123321"));
        Assertions.assertFalse(Methods.esPalindromo("palindromo"));
        Assertions.assertFalse(Methods.esPalindromo("soldado"));
    }
    
    @Test
    @DisplayName("Test de suma de digitos numericos en una cadena")
    void testSumaDigitosNumericos() {
        Assertions.assertEquals(34, Methods.sumaDigitosNumericos("A874B69Q"));
        Assertions.assertEquals(0, Methods.sumaDigitosNumericos("AQSDERF"));
        Assertions.assertEquals(0, Methods.sumaDigitosNumericos(""));
        Assertions.assertEquals(6, Methods.sumaDigitosNumericos("123"));
    }
}
