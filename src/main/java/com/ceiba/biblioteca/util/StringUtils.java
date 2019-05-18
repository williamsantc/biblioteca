/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.util;

/**
 *
 * @author willi
 */
public class StringUtils {
 
    public static String printString(String wordOne, String wordTwo) {
        if(!isEmptyString(wordOne)) {
            return wordOne;
        } else if(!isEmptyString(wordTwo)) {
            return wordTwo;
        }
        return "";
    }
    
    public static boolean isEmptyString(String word) {
        return word == null || word.length() == 0;
    }
}
