package com.danrocha.cde.services;


import com.danrocha.cde.entities.Empresa;
import com.danrocha.cde.repositories.EmpresaRepository;
import com.danrocha.cde.utils.Transacional;
import jakarta.inject.Inject;

import java.io.Serial;
import java.io.Serializable;

public class CadastroEmpresaService implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaRepository repo;

    @Transacional
    public Empresa salvar(Empresa empresa) {
        return repo.salvarOuAtualizar(empresa);
    }

    @Transacional
    public void excluir(Empresa empresa) {
        repo.excluir(empresa);
    }

}
