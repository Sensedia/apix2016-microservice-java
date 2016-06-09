/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sensedia.carteira.bean;

import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Hands ON APIX 2016.
 * 
 * @author Sensedia
 */
@Entity
@Table(name="T_CLIENTE")
public class ClienteBean {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PESSOA_ID")
    private PessoaBean pessoa;
    
    @OneToMany(mappedBy="pessoa", fetch=FetchType.LAZY)
    private Set<ContatoBean> contatos;
    
    private String senha;

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

    public Set<ContatoBean> getContatos() {
        return contatos;
    }

    public void setContatos(Set<ContatoBean> contatos) {
        this.contatos = contatos;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
