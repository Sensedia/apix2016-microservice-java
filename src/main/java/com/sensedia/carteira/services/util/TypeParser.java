/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sensedia.carteira.services.util;

import com.sensedia.carteira.bean.ClienteBean;
import com.sensedia.carteira.bean.ContatoBean;
import com.sensedia.carteira.bean.EnderecoBean;
import com.sensedia.carteira.bean.PessoaBean;
import com.sensedia.carteira.bean.TelefoneBean;
import com.sensedia.carteira.cliente.ClienteRequestType;
import com.sensedia.carteira.cliente.ClienteResponseType;
import com.sensedia.carteira.cliente.ContatoRequestType;
import com.sensedia.carteira.cliente.ContatoType;
import com.sensedia.carteira.cliente.ContatosType;
import com.sensedia.carteira.cliente.EnderecoRequestType;
import com.sensedia.carteira.cliente.EnderecoType;
import com.sensedia.carteira.cliente.EnderecosType;
import com.sensedia.carteira.cliente.TelefoneRequestType;
import com.sensedia.carteira.cliente.TelefoneType;
import com.sensedia.carteira.cliente.TelefonesType;
import java.util.List;
import java.util.Set;

/**
 * Hands ON APIX 2016.
 * 
 * @author Sensedia
 */
public class TypeParser {

    private TypeParser() {
    }
    
    private static TypeParser instance = null;
    public static TypeParser getInstance() {
        if (instance == null) {
           instance = new TypeParser();
        }
        return instance;
    }
    
    public ClienteBean toClienteBean(ClienteRequestType type) {
        
        PessoaBean pessoa = new PessoaBean();
        pessoa.setId(type.getId());
        pessoa.setNome(type.getNome());
        pessoa.setCpf(type.getCpf());
        
        ClienteBean cliente = new ClienteBean();
        cliente.setSenha(type.getSenha());
        cliente.setPessoa(pessoa);
        
        return cliente;
        
    }
    
    public ClienteResponseType toClienteResponseType(ClienteBean bean) {
    
        ClienteResponseType type = new ClienteResponseType();
        
        if (bean != null) {
            type.setId(bean.getPessoa().getId());
            type.setNome(bean.getPessoa().getNome());
            type.setCpf(bean.getPessoa().getCpf());

            type.setEnderecos(toEnderecosType(bean.getPessoa().getEnderecos()));
            type.setTelefones(toTelefonesType(bean.getPessoa().getTelefones()));
        }
        
        return type;
        
    }
    
    public EnderecosType toEnderecosType(Set<EnderecoBean> beans) {
    
        EnderecosType enderecosType = null;
        
        if (beans != null) {
            enderecosType = new EnderecosType();
            for (EnderecoBean endereco : beans) {
                enderecosType.getEndereco().add(toEnderecoType(endereco));
            }
        }
        
        return enderecosType;
    
    }
    
    public EnderecoType toEnderecoType(EnderecoBean bean) {
        
        EnderecoType type = new EnderecoType();
        type.setId(bean.getId());
        type.setBairro(bean.getBairro());
        type.setCidade(bean.getCidade());
        type.setEstado(bean.getEstado());
        type.setLogradouro(bean.getLogradouro());
        type.setNumero(bean.getNumero());
        
        return type;
        
    }
    
    public ContatoBean toContatoBean(ContatoRequestType type) {
    
        PessoaBean cliente = new PessoaBean();
        cliente.setId(type.getIdCliente());
        
        PessoaBean pessoa = new PessoaBean();
        pessoa.setId(type.getContato().getId());
        pessoa.setNome(type.getContato().getNome());
        pessoa.setCpf(type.getContato().getCpf());
        
        ContatoBean contato = new ContatoBean();
        contato.setCliente(cliente);
        contato.setPessoa(pessoa);
        
        return contato;
    
    }
    
    public ContatoType toContatoType(ContatoBean bean) {
    
        ContatoType type = new ContatoType();
        type.setId(bean.getPessoa().getId());
        type.setCpf(bean.getPessoa().getCpf());
        type.setNome(bean.getPessoa().getNome());
        
        return type;
        
    }
    
    public ContatosType toContatosType(List<ContatoBean> beans) {
    
        ContatosType contatosType = null;
        
        if (beans != null) {
            contatosType = new ContatosType();
            for (ContatoBean endereco : beans) {
                contatosType.getContato().add(toContatoType(endereco));
            }
        }
        
        return contatosType;
    
    }
    
    public TelefonesType toTelefonesType(Set<TelefoneBean> beans) {
    
        TelefonesType telefonesType = null;
        
        if (beans != null) {
            telefonesType = new TelefonesType();
            for (TelefoneBean telefone : beans) {
                telefonesType.getTelefone().add(toTelefoneType(telefone));
            }
        }
        
        return telefonesType;
    
    }
    
    public TelefoneBean toTelefoneBean(TelefoneRequestType type) {
    
        TelefoneBean bean = new TelefoneBean();
        bean.setId(type.getTelefone().getId());
        bean.setNumero(type.getTelefone().getNumero());
        bean.setTipo(type.getTelefone().getTipo());
        
        PessoaBean pessoa = new PessoaBean();
        pessoa.setId(type.getIdCliente());
        
        bean.setPessoa(pessoa);
        
        return bean;
    
    }
    
    public TelefoneType toTelefoneType(TelefoneBean bean) {
    
        TelefoneType type = new TelefoneType();
        type.setId(bean.getId());
        type.setNumero(bean.getNumero());
        type.setTipo(bean.getTipo());
        
        return type;
        
    }
    
    public EnderecoBean toEnderecoBean(EnderecoRequestType type) {
    
        EnderecoBean bean = new EnderecoBean();
        
        bean.setId(type.getEndereco().getId());
        bean.setBairro(type.getEndereco().getBairro());
        bean.setCep(type.getEndereco().getCep());
        bean.setCidade(type.getEndereco().getCidade());
        bean.setEstado(type.getEndereco().getEstado());
        bean.setLogradouro(type.getEndereco().getLogradouro());
        bean.setNumero(type.getEndereco().getNumero());
        
        PessoaBean pessoa = new PessoaBean();
        pessoa.setId(type.getIdCliente());
        
        bean.setPessoa(pessoa);        
        
        return bean;
    
    }
    
    
    
}
