package com.danrocha.cde.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

public class FacesMessages implements Serializable {
    private static final long serialVersionUID = 1L;

    public void info(String msg) {
        this.add(msg, FacesMessage.SEVERITY_INFO);
    }

    public void error(String msg) {
        this.add(msg, FacesMessage.SEVERITY_ERROR);
    }

    public void warning(String msg) {
        this.add(msg, FacesMessage.SEVERITY_WARN);
    }

    private void add(String msg, FacesMessage.Severity level) {
        FacesMessage facesMessage = new FacesMessage(msg);
        facesMessage.setSeverity(level);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }


}
