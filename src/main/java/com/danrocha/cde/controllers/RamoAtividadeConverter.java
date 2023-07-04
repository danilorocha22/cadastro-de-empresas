package com.danrocha.cde.controllers;

import com.danrocha.cde.entities.RamoAtividade;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;

public class RamoAtividadeConverter implements Converter<RamoAtividade> {

    private final List<RamoAtividade> listaRamoAtividades;

    public RamoAtividadeConverter(List<RamoAtividade> listaRamoAtividades) {
        this.listaRamoAtividades = listaRamoAtividades;
    }

    @Override
    public RamoAtividade getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null) {
            return null;
        }
        for (RamoAtividade ramoAtividade : listaRamoAtividades) {
            if (ramoAtividade.getId().toString().equals(value)) {
                return ramoAtividade;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, RamoAtividade ramoAtividade) {
        if (ramoAtividade == null) {
            return "";
        }
        return ramoAtividade.getId().toString();
    }

}
