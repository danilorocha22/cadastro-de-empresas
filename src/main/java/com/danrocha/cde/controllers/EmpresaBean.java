package com.danrocha.cde.controllers;

import com.danrocha.cde.entities.Empresa;
import com.danrocha.cde.repositories.EmpresaRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

//@Model //bean gerenciado e com escopo de requisição, ou seja, combina as anotações @Named e @RequestScoped
//@ManagedBean //anotação mais antiga para gerenciar os beans
@Named //Define que o bean é gerenciado. Esta anotação substituiu a anotação @ManagedBean
@ViewScoped //Define que o bean tem escopo de requisição
public class EmpresaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaRepository repo;
    private List<Empresa> empresas;

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void listarEmpresas() {
        this.empresas = this.repo.listarTodas();
    }

}
