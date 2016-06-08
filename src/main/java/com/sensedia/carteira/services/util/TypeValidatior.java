/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sensedia.carteira.services.util;

/**
 * Hands ON APIX 2016.
 * 
 * @author Sensedia
 */
public class TypeValidatior {

    private TypeValidatior() {
    }
    
    private static TypeValidatior instance = null;
    public static TypeValidatior getInstance() {
        if (instance == null) {
           instance = new TypeValidatior();
        }
        return instance;
    }
    
    public boolean isValidCPF(String cpf) {

        if ((cpf == null) || (cpf.length() != 11) || (!cpf.matches("^[0-9]+$"))) {
            throw new RuntimeException("O campo CPF deve conter apenas caracteres numéricos e possuir 11 posições.");
        }
        
        return true;
        
    }

    public boolean isValidSenha(String senha) {

        if ((senha == null) || (senha.length() < 6) || (senha.length() > 10)) {
            throw new RuntimeException("O campo Senha deve possuir de 6 à 10 posições.");
        }
        
        return true;
        
    }

}
