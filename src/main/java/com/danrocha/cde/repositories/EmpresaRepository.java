package com.danrocha.cde.repositories;

import com.danrocha.cde.entities.Empresa;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class EmpresaRepository implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private transient EntityManager em;
    
    public EmpresaRepository() {}

    public EmpresaRepository(EntityManager em) {
	super();
	this.em = em;
    }
    
    public Empresa buscarPeloId(Long id) {
	return this.em.find(Empresa.class, id);
    }
    
    public List<Empresa> buscarPeloNome(String nome) {
	//Usando JPQL
	String query = "from Empresa where nomeFantasia like :nomeFantasia";
	TypedQuery<Empresa> tQuery = this.em.createQuery(query, Empresa.class); 
	tQuery.setParameter("nomeFantasia", "%" + nome + "%"); //'%' no início, busca qualquer nome pelo prefixo e no final qualquer nome pelo sufixo
	return tQuery.getResultList();
    }
    
    public Empresa salvarOuAtualizar(Empresa empresa) {
	return this.em.merge(empresa);
    }
        
    public void excluir(Long id) {;
      this.em.remove(this.buscarPeloId(id));
    }
    
    public void excluir(Empresa empresa) {
	this.excluir(empresa.getId());
    }
    
    
    
    

}
