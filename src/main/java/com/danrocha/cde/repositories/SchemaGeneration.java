package com.danrocha.cde.repositories;

import java.util.List;

import com.danrocha.cde.entities.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SchemaGeneration {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        List<Empresa> empresas = em.createQuery("from Empresa", Empresa.class).getResultList();

        empresas.forEach(System.out::println);

        em.close();
        emf.close();
    }

}
