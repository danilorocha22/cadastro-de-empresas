package com.danrocha.cde.controllers;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

//@Model //bean gerenciado e com escopo de requisição, ou seja, combina as anotações @Named e @RequestScoped
//@ManagedBean //anotação mais antiga para gerenciar os beans
@Named //Define que o bean é gerenciado. Esta anotação substituiu a anotação @ManagedBean
@ViewScoped //Define que o bean tem escopo de requisição
public class EmpresaBean implements Serializable {
    private static final long serialVersionUID = 1L;


}
