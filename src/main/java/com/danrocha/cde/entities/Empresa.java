package com.danrocha.cde.entities;

import com.danrocha.cde.enums.TipoEmpresa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Campo obrigatório")
    @Column(name = "nome_fantasia", nullable = false, length = 80)
    private String nomeFantasia;
    
    @NotBlank(message = "Campo obrigatório.")
    @Column(name = "razao_social", nullable = false, length = 120)
    private String razaoSocial;
    
    @NotBlank(message = "Campo obrigatório.")
    @Column(nullable = false, length = 18) //11111111000111
    private String cnpj;
    
    @NotNull(message = "Campo obrigatório.")
    //@JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fundado_em")
    private LocalDate fundadoEm;
    
    @NotNull(message = "Campo obrigatório.")
    @Enumerated(EnumType.STRING)
    private TipoEmpresa tipo;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "ramo_atividade_id")
    private RamoAtividade ramoAtividade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public @NotNull LocalDate getFundadoEm() {
        return fundadoEm;
    }

    public void setFundadoEm(@NotNull LocalDate fundadoEm) {
        this.fundadoEm = fundadoEm;
    }

    public @NotNull TipoEmpresa getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpresa tipo) {
        this.tipo = tipo;
    }

    public @NotNull RamoAtividade getRamoAtividade() {
        return ramoAtividade;
    }

    public void setRamoAtividade(@NotNull RamoAtividade ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    @Override
    public String toString() {
	return "Empresa [id=" + id + ", nomeFantasia=" + nomeFantasia + ", razaoSocial=" + razaoSocial + ", cnpj="
		+ cnpj + ", fundadoEm=" + fundadoEm + ", tipo=" + tipo + ", ramoAtividade=" + ramoAtividade + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(cnpj, id);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Empresa other = (Empresa) obj;
	return Objects.equals(cnpj, other.cnpj) && Objects.equals(id, other.id);
    }
    
    

}
