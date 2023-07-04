package com.danrocha.cde.controllers;

import com.danrocha.cde.entities.Empresa;
import com.danrocha.cde.entities.RamoAtividade;
import com.danrocha.cde.enums.TipoEmpresa;
import com.danrocha.cde.repositories.EmpresaRepository;
import com.danrocha.cde.repositories.RamoAtividadeRepository;
import com.danrocha.cde.utils.FacesMessages;

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
    private EmpresaRepository empresaRepo;

    @Inject
    private RamoAtividadeRepository ramoRepo;

    @Inject
    private FacesMessages messages;

    private List<Empresa> empresas;

    private List<RamoAtividade> ramoAtividades;

    private String termoPesquisa;

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void listarEmpresas() {
        this.empresas = this.empresaRepo.listarTodas();
    }

    public void pesquisar() {
        this.empresas = this.empresaRepo.pesquisar(this.termoPesquisa);

        if (this.empresas.isEmpty()) {
            this.messages.info("Sua consulta não retornou registros.");
        }
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public TipoEmpresa[] getTiposEmpresa() {
        return TipoEmpresa.values();
    }

    public List<RamoAtividade> getRamoAtividades() {
        return ramoAtividades;
    }

    public void listarRamoAtividades() {
        this.ramoAtividades = this.ramoRepo.listarTodas();
    }
}
