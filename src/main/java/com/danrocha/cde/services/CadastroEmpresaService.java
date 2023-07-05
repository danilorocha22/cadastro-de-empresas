package com.danrocha.cde.services;

import com.danrocha.cde.entities.Empresa;
import com.danrocha.cde.repositories.EmpresaRepository;
import com.danrocha.cde.utils.Transacional;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroEmpresaService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaRepository repo;

    @Transacional
    public Empresa salvarOuAtualizar(Empresa empresa) {
        return repo.salvarOuAtualizar(empresa);
    }

    @Transacional
    public void excluir(Empresa empresa) {
        repo.excluir(empresa);
    }

}
