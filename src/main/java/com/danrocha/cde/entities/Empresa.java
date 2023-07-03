package com.danrocha.cde.entities;

import com.danrocha.cde.enums.TipoEmpresa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_fantasia", length = 80)
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

    @Column(precision = 10, scale = 2)
    private BigDecimal faturamento;

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
