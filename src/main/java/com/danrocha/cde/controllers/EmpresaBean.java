package com.danrocha.cde.controllers;

import com.danrocha.cde.entities.Empresa;
import com.danrocha.cde.entities.RamoAtividade;
import com.danrocha.cde.enums.TipoEmpresa;
import com.danrocha.cde.repositories.EmpresaRepository;
import com.danrocha.cde.repositories.RamoAtividadeRepository;
import com.danrocha.cde.services.CadastroEmpresaService;
import com.danrocha.cde.utils.FacesMessages;
import org.primefaces.PrimeFaces;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    @Inject
    private CadastroEmpresaService empresaService;

    private List<Empresa> empresas;

    private String termoPesquisa;

    private transient Converter<RamoAtividade> ramoAtividadeConverter;

    private Empresa empresa;

    /*Getters e Setters*/

    public List<Empresa> getEmpresas() {
        return empresas;
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

    public Converter<RamoAtividade> getRamoAtividadeConverter() {
        return ramoAtividadeConverter;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /*Métodos*/

    public void listarEmpresas() {
        this.empresas.addAll(this.empresaRepo.listarTodas());
    }

    public List<RamoAtividade> completarRamoAtividade(String termo) {
        List<RamoAtividade> listaAtividades = this.ramoRepo.pesquisar(termo);
        this.ramoAtividadeConverter = new RamoAtividadeConverter(listaAtividades);
        return listaAtividades;
    }

    public void pesquisar() {
        this.empresas.addAll(this.empresaRepo.pesquisar(this.termoPesquisa));
        if (this.empresas.isEmpty()) {
            this.messages.warning("Sua consulta não retornou registros.");
        }
    }

    public void salvar() {
        Empresa empresaRegistro = this.empresaService.salvarOuAtualizar(this.empresa);
        this.atualizarRegistro();//Atualizar a lista de registro da tabela
        this.atualizarVisualizacaoTabela(); //Atualizar a view da tabela
        if (Objects.nonNull(this.empresa.getId())) {
            this.alertMessages("Registro atualizado com sucesso: ", empresaRegistro);
        } else {
            this.alertMessages("Registro salvo com sucesso: ", empresaRegistro);
        }
    }

    public void excluir() {
        this.empresaService.excluir(this.empresa);
        this.setEmpresa(null);
        this.atualizarRegistro();
        this.alertMessages("Registro excluído com sucesso: ", this.empresa);
        //this.atualizarVisualizacaoTabela();
    }

    public void prepararNovaEmpresa() {
        this.empresa = new Empresa();
    }

    public void prepararEdicaoEmpresa() {
        ramoAtividadeConverter = new RamoAtividadeConverter(List.of(this.empresa.getRamoAtividade()));
    }

    public boolean isEmpresaSelecionada() {
        return this.empresa != null && this.empresa.getId() != null;
    }

    private void atualizarRegistro() {
        if (jaHouvePesquisa()) {
            this.pesquisar();
        } else {
            this.listarEmpresas();
        }
    }

    private boolean jaHouvePesquisa() {
        return termoPesquisa != null && !termoPesquisa.trim().isEmpty();
    }

    private void atualizarVisualizacaoTabela() {
        PrimeFaces.current().ajax().update(List.of("form:empresaTable", "form:messagesForm"));
    }

    private void alertMessages(String msg, Empresa emp) {
        this.messages.info(msg + emp.getRazaoSocial());
    }

}
