package com.danrocha.cde.entities;

import com.danrocha.cde.enums.TipoEmpresa;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_fantasia", length = 80)
    private String nomeFantasia;
    
    @NotBlank
    @Column(name = "razao_social", nullable = false, length = 120)
    private String razaoSocial;

    @CNPJ
    /*@NotBlank(message = "é obrigatório.")*/
    @Column(nullable = false, length = 18) //11111111000111
    private String cnpj;
    
    @NotNull
    //@JsonFormat(pattern = "dd/MM/yyyy")
    @PastOrPresent(message = "não é permitido cadastrar uma data futura.")
    @Column(name = "fundado_em", nullable = false)
    private LocalDate fundadoEm;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoEmpresa tipo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ramo_atividade_id")
    private RamoAtividade ramoAtividade;

    /*@Column(precision = 10, scale = 2)
    private BigDecimal faturamento;*/

    @Override
    public String toString() {
	return "Empresa [id=" + id + ", nomeFantasia=" + nomeFantasia + ", razaoSocial=" + razaoSocial + ", cnpj="
		+ cnpj + ", fundadoEm=" + fundadoEm + ", tipo=" + tipo + ", ramoAtividade=" + ramoAtividade + "]";
    }

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

    public LocalDate getFundadoEm() {
        return fundadoEm;
    }

    public void setFundadoEm(LocalDate fundadoEm) {
        this.fundadoEm = fundadoEm;
    }

    public TipoEmpresa getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpresa tipo) {
        this.tipo = tipo;
    }

    public RamoAtividade getRamoAtividade() {
        return ramoAtividade;
    }

    public void setRamoAtividade(RamoAtividade ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    /*public BigDecimal getFaturamento() {
        return faturamento;
    }*/

   /* public void setFaturamento(BigDecimal faturamento) {
        this.faturamento = faturamento;
    }*/

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
