/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sensedia.carteira.services;

import com.sensedia.carteira.bean.ClienteBean;
import com.sensedia.carteira.bean.ContatoBean;
import com.sensedia.carteira.bean.EnderecoBean;
import com.sensedia.carteira.bean.TelefoneBean;
import com.sensedia.carteira.cliente.ClienteRequestType;
import com.sensedia.carteira.cliente.ClienteResponseType;
import com.sensedia.carteira.cliente.ConsularClientePorCPFRequestType;
import com.sensedia.carteira.cliente.ContatoRequestType;
import com.sensedia.carteira.cliente.ContatoType;
import com.sensedia.carteira.cliente.ContatosType;
import com.sensedia.carteira.cliente.EnderecoRequestType;
import com.sensedia.carteira.cliente.EnderecoType;
import com.sensedia.carteira.cliente.ListarContatosRequestType;
import com.sensedia.carteira.cliente.TelefoneRequestType;
import com.sensedia.carteira.cliente.TelefoneType;
import com.sensedia.carteira.cliente.ValidarClienteRequestType;
import com.sensedia.carteira.dao.ClienteDAO;
import com.sensedia.carteira.services.util.TypeParser;
import com.sensedia.carteira.services.util.TypeValidatior;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * Hands ON APIX 2016.
 * 
 * @author Sensedia
 */
@Stateless
@WebService(serviceName = "ClienteService", 
            portName = "ClientePort", 
            endpointInterface = "com.sensedia.carteira.clienteservice.ClientePortType", 
            targetNamespace = "http://www.sensedia.com/carteira/ClienteService", 
            wsdlLocation = "WEB-INF/wsdl/ClienteService.wsdl")
public class ClienteWS {

    @EJB
    ClienteDAO clienteDAO;
    
    public ClienteResponseType salvarCliente(ClienteRequestType in) {
        
        TypeValidatior.getInstance().isValidCPF(in.getCpf());
        TypeValidatior.getInstance().isValidSenha(in.getSenha());

        ClienteBean cliente = clienteDAO.salvar(TypeParser.getInstance().toClienteBean(in));
        return TypeParser.getInstance().toClienteResponseType(cliente);

    }

    public ClienteResponseType consultarClientePorCPF(ConsularClientePorCPFRequestType in) {
        
        TypeValidatior.getInstance().isValidCPF(in.getCpf());
                
        ClienteBean cliente = clienteDAO.buscarPorCPF(in.getCpf());
        return TypeParser.getInstance().toClienteResponseType(cliente);
        
    }

    public ClienteResponseType validarCliente(ValidarClienteRequestType in) {
        
        TypeValidatior.getInstance().isValidCPF(in.getCpf());
        TypeValidatior.getInstance().isValidSenha(in.getSenha());
        
        ClienteBean cliente = clienteDAO.buscarPorCPFeSenha(in.getCpf(), in.getSenha());
        return TypeParser.getInstance().toClienteResponseType(cliente);
        
    }

    public ContatoType salvarContato(ContatoRequestType in) {
        
        if (in.getContato() != null) {
            TypeValidatior.getInstance().isValidCPF(in.getContato().getCpf());
        }
        
        ContatoBean contato = clienteDAO.salvarContato(TypeParser.getInstance().toContatoBean(in));
        return TypeParser.getInstance().toContatoType(contato);
        
    }

    public ContatosType listarContatos(ListarContatosRequestType in) {
        
        TypeValidatior.getInstance().isValidCPF(in.getCpf());
        
        return TypeParser.getInstance().toContatosType(clienteDAO.listarContatos(in.getCpf()));
        
    }

    public TelefoneType salvarTelefone(TelefoneRequestType in) {
        
        TelefoneBean telefone = clienteDAO.salvarTelefone(TypeParser.getInstance().toTelefoneBean(in));
        return TypeParser.getInstance().toTelefoneType(telefone);        
        
    }

    public EnderecoType salvarEndereco(EnderecoRequestType in) {
        
        EnderecoBean endereco = clienteDAO.salvarEndereco(TypeParser.getInstance().toEnderecoBean(in));
        return TypeParser.getInstance().toEnderecoType(endereco);
        
    }
    
}
