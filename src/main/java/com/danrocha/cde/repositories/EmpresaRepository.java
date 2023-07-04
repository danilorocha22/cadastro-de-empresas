package com.danrocha.cde.repositories;

import com.danrocha.cde.entities.Empresa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class EmpresaRepository implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private transient EntityManager em;

    public EmpresaRepository() {
    }

    public EmpresaRepository(EntityManager em) {
        super();
        this.em = em;
    }

    public Empresa buscarPeloId(Long id) {
        return this.em.find(Empresa.class, id);
    }

    public List<Empresa> pesquisarPeloNome(String nome) {
        //Usando JPQL
        String query = "FROM Empresa WHERE nomeFantasia LIKE :nomeFantasia";
        TypedQuery<Empresa> tQuery = this.em.createQuery(query, Empresa.class);
        tQuery.setParameter("nomeFantasia", "%" + nome + "%"); //'%' no início, busca qualquer nome pelo prefixo e no final qualquer nome pelo sufixo
        return tQuery.getResultList();
    }

    public List<Empresa> listarTodas() {
        return this.em.createQuery("FROM Empresa", Empresa.class).getResultList();
    }

    public Empresa salvarOuAtualizar(Empresa empresa) {
        return this.em.merge(empresa);
    }

    public void excluir(Long id) {
        this.em.remove(this.buscarPeloId(id));
    }

    public void excluir(Empresa empresa) {
        this.excluir(empresa.getId());
    }


}
