package com.danrocha.cde.repositories;

import com.danrocha.cde.entities.Empresa;
import com.danrocha.cde.entities.RamoAtividade;
import com.danrocha.cde.enums.TipoEmpresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class CamadaPersistencia {
    public static void main(String[] args) {
        // Fábrica de Gerenciador de Entidades
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();// Iniciar Transação, sem este início, não conseguimos fazer inserções,
        // Alterações e exclusões. Ou seja, apenas consultas

        // Repositórios
        RamoAtividadeRepository atividadeRepo = new RamoAtividadeRepository(em);
        EmpresaRepository empresaRepo = new EmpresaRepository(em);

        // Buscando dados no banco
        List<RamoAtividade> listaAtividades = atividadeRepo.buscarPeloNome(""); // ("") buscando todos os dados
        List<Empresa> listaEmpresas = empresaRepo.buscarPeloNome("");
        listaEmpresas.forEach(System.out::println);

        // Criando empresa
        Empresa empresa = new Empresa();
        empresa.setNomeFantasia("DRS Tecnologias");
        empresa.setCnpj("01.001.001/0001-01");
        empresa.setRazaoSocial("DRS Tecnologias SA");
        empresa.setTipo(TipoEmpresa.SA);
        empresa.setFundadoEm(LocalDate.now());
        empresa.setRamoAtividade(listaAtividades.get(1));

        // Salvando empresa
        empresaRepo.salvarOuAtualizar(empresa);

        // Comitando transação
        em.getTransaction().commit();

        // Verifica se a inserção funcionou
        List<Empresa> listaEmpresas2 = empresaRepo.buscarPeloNome("");
        listaEmpresas2.forEach(System.out::println);

        // Fechando
        em.close();
        emf.close();

    }

}
