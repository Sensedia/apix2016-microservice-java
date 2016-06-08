/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sensedia.carteira.dao;

import com.sensedia.carteira.bean.ClienteBean;
import com.sensedia.carteira.bean.ContatoBean;
import com.sensedia.carteira.bean.EnderecoBean;
import com.sensedia.carteira.bean.PessoaBean;
import com.sensedia.carteira.bean.TelefoneBean;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Hands ON APIX 2016.
 * 
 * @author Sensedia
 */
@Stateless
public class ClienteDAO {
    
    @PersistenceContext(unitName = "MysqlDS_PU")
    EntityManager em;
    
    public ClienteBean buscarPorCPF(String cpf) {
        
        String hql = "SELECT c FROM ClienteBean c"
                   + "  JOIN FETCH c.pessoa p"
                   + "  LEFT JOIN FETCH c.enderecos e"
                   + "  LEFT JOIN FETCH c.telefones t"
                   + " WHERE p.cpf = :cpf";
        
        Query query = em.createQuery(hql, ClienteBean.class);
        query.setParameter("cpf", cpf);
        
        try {
            return (ClienteBean) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
    
    private PessoaBean buscarPessoaPorCPF(String cpf) {
        
        String hql = "SELECT p FROM PessoaBean p "
                   + " WHERE p.cpf = :cpf";
        
        Query query = em.createQuery(hql, PessoaBean.class);
        query.setParameter("cpf", cpf);
        
        try {
            return (PessoaBean) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }    
    
    public ClienteBean buscarPorCPFeSenha(String cpf, String senha) {
        
        String hql = "SELECT c FROM ClienteBean c"
                   + "  JOIN FETCH c.pessoa p"
                   + "  LEFT JOIN FETCH c.enderecos e"
                   + "  LEFT JOIN FETCH c.telefones t"
                   + " WHERE p.cpf = :cpf"
                   + "   AND c.senha = :senha";
        
        Query query = em.createQuery(hql, ClienteBean.class);
        query.setParameter("cpf", cpf);
        query.setParameter("senha", senha);
        
        try {
            return (ClienteBean) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }    
    
    public ClienteBean buscarPorID(Long id) {
        
        String hql = "SELECT c FROM ClienteBean c"
                   + "  JOIN FETCH c.pessoa p"
                   + " WHERE p.id = :id";
        
        Query query = em.createQuery(hql, ClienteBean.class);
        query.setParameter("id", id);

        return (ClienteBean) query.getSingleResult();
    }
    
    public ContatoBean buscarContatoPorID(Long id) {
        
        String hql = "SELECT c FROM ContatoBean c"
                   + "  JOIN FETCH c.pessoa p"
                   + " WHERE p.id = :id";
        
        Query query = em.createQuery(hql, ContatoBean.class);
        query.setParameter("id", id);

        return (ContatoBean) query.getSingleResult();
    }    
    
    public ClienteBean salvar(ClienteBean cliente) {
        
        if (cliente.getPessoa().getId() == null) {
            
            PessoaBean pessoa = buscarPessoaPorCPF(cliente.getPessoa().getCpf());
            
            if (pessoa == null) {
                em.persist(cliente.getPessoa());
            } else {
                cliente.getPessoa().setId(pessoa.getId());
                em.merge(cliente.getPessoa());
            }
            
        } else {
            em.merge(cliente.getPessoa());
        }
        
        ClienteBean c = null;
        try {
            c = buscarPorID(cliente.getPessoa().getId());
            cliente.setId(c.getId());
        } catch(NoResultException e) {}
        
        if (cliente.getId() == null) {
            em.persist(cliente);
        } else {
            em.merge(cliente);
        }
        
        return cliente;
    
    }
    
    public ContatoBean salvarContato(ContatoBean contato) {
        
        if (contato.getPessoa().getId() == null) {
            
            PessoaBean pessoa = buscarPessoaPorCPF(contato.getPessoa().getCpf());
            
            if (pessoa == null) {
                em.persist(contato.getPessoa());
            } else {
                contato.getPessoa().setId(pessoa.getId());
                em.merge(contato.getPessoa());
            }
                
        } else {
            em.merge(contato.getPessoa());
        }
        
        ContatoBean c = null;
        try {
            c = buscarContatoPorID(contato.getPessoa().getId());
            contato.setId(c.getId());
        } catch(NoResultException e) {}
        
        if (contato.getId() == null) {
            em.persist(contato);
        } else {
            em.merge(contato);
        }        
        
        return contato;
        
    }
    
    public List<ContatoBean> listarContatos(String cpf) {
        
        String hql = "SELECT co FROM ContatoBean co"
                   + "  JOIN co.cliente cl"
                   + " WHERE cl.cpf = :cpf";
        
        Query query = em.createQuery(hql, ContatoBean.class);
        query.setParameter("cpf", cpf);

        return (List<ContatoBean>) query.getResultList();
    }
    
    public EnderecoBean salvarEndereco(EnderecoBean endereco) {
    
        if (endereco.getId() == null) {
            em.persist(endereco);
        } else {
            em.merge(endereco);
        }
        
        return endereco;
        
    }
    
    public TelefoneBean salvarTelefone(TelefoneBean telefone) {
    
        if (telefone.getId() == null) {
            em.persist(telefone);
        } else {
            em.merge(telefone);
        }
        
        return telefone;
        
    }

}
