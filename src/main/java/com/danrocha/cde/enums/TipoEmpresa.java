package com.danrocha.cde.enums;

public enum TipoEmpresa {
    
    MEI("Microempreendedor Individual"),
    EIRELI("Empresa Individual de Responsabilidade Limitada"),
    LTDA("Empresa Limitada"),
    SA("Sociedade Anônima");
    
    private final String descricao;

    private TipoEmpresa(String descricao) {
	this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    

}
