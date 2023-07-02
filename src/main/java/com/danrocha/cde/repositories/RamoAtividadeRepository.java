package com.danrocha.cde.repositories;

import com.danrocha.cde.entities.RamoAtividade;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class RamoAtividadeRepository implements Serializable {
    @Serial
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
