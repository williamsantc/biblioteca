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
    
        
    public static int sumaisbn (String c){
        int cont=0;
        for(int i=0;i<c.length();i++){
            if (c.charAt(i)>=48&&c.charAt(i)<=57)
                cont=cont+(c.charAt(i)-48);
        }
        return cont;
    }
    
    public static boolean palindromo(String c){
        int j=c.length()-1;
        for(int i=0;i<(int)(c.length()/2);i++){
            if (c.charAt(i)!=c.charAt(j))return false;
            j--;
        }
        return true;
    }
    
}

