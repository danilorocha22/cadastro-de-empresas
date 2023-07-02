package com.danrocha.cde.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

//Esta anotação cria beans que são compartilhados por toda a aplicação e têm
//uma única instância durante todo o tempo de vida da aplicação.
@ApplicationScoped
public class EntityManagerProducer {

    private final EntityManagerFactory emf;

    public EntityManagerProducer() {
        this.emf = Persistence.createEntityManagerFactory("default");
    }

    /*Anotações do CDI*/
    @Produces //Responsável por produzir uma instância de um determinado tipo.
    @RequestScoped //Uma instância será criada para cada solicitação do cliente.
    public EntityManager createEntityManager() {
        return this.emf.createEntityManager();
    }

    /*O método anotado com @Disposes é chamado pelo CDI quando a instância produzida
    pelo método @Produces precisa ser descartada.*/
    public void closeEntityManager(@Disposes EntityManager em) {
        em.close();
    }


}
