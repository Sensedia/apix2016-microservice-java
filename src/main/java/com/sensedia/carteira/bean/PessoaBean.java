/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sensedia.carteira.bean;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Hands ON APIX 2016.
 * 
 * @author Sensedia
 */
@Entity
@Table(name = "T_PESSOA")
public class PessoaBean {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    
    @OneToMany(mappedBy="pessoa", fetch=FetchType.LAZY)
    private Set<TelefoneBean> telefones;
    
    @OneToMany(mappedBy="pessoa", fetch=FetchType.LAZY)
    private Set<EnderecoBean> enderecos;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Set<TelefoneBean> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<TelefoneBean> telefones) {
        this.telefones = telefones;
    }

    public Set<EnderecoBean> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<EnderecoBean> enderecos) {
        this.enderecos = enderecos;
    }    
    
}
