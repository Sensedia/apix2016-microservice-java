/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sensedia.carteira.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Hands ON APIX 2016.
 * 
 * @author Sensedia
 */
@Entity
@Table(name="T_CONTATO")
public class ContatoBean {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PESSOA_ID")
    private PessoaBean pessoa;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENTE_ID")
    private PessoaBean cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PessoaBean getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaBean pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaBean getCliente() {
        return cliente;
    }

    public void setCliente(PessoaBean cliente) {
        this.cliente = cliente;
    }
    
}
