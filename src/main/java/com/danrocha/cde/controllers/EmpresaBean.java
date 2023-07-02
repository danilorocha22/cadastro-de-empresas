package com.danrocha.cde.controllers;

import com.danrocha.cde.entities.Empresa;
import com.danrocha.cde.enums.TipoEmpresa;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;

//@Model //bean gerenciado e com escopo de requisição, ou seja, combina as anotações @Named e @RequestScoped
//@ManagedBean //anotação mais antiga para gerenciar os beans
@Named //Define que o bean é gerenciado. Esta anotação substituiu a anotação @ManagedBean
@ViewScoped //Define que o bean tem escopo de requisição
public class EmpresaBean implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Empresa empresa = new Empresa();

    public Empresa getEmpresa() {
        return empresa;
    }

    public TipoEmpresa[] getTiposEmpresa() {
        return TipoEmpresa.values();
    }

    public void salvar() {
        System.out.printf(
                "Razão Social: %s %n Nome Fantasia: %s %n CNPJ: %s%n",
                empresa.getRazaoSocial(), empresa.getNomeFantasia(), empresa.getCnpj());
    }

}
