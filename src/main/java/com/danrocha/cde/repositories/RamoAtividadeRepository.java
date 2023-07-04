package com.danrocha.cde.repositories;

import com.danrocha.cde.entities.RamoAtividade;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class RamoAtividadeRepository implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private transient EntityManager em;

    public RamoAtividadeRepository() {
    }

    public RamoAtividadeRepository(EntityManager em) {
        super();
        this.em = em;
    }

    public List<RamoAtividade> buscarPeloNome(String descricao) {
        //Usando Criteria
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<RamoAtividade> cQuery = builder.createQuery(RamoAtividade.class);
        Root<RamoAtividade> root = cQuery.from(RamoAtividade.class);
        cQuery.select(root);
        cQuery.where(builder.like(root.get("descricao"), "%" + descricao + "%"));
        TypedQuery<RamoAtividade> tQuery = this.em.createQuery(cQuery);
        return tQuery.getResultList();
    }


}
