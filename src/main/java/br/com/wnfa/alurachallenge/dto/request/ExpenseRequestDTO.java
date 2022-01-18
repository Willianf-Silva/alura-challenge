package br.com.wnfa.alurachallenge.dto.request;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wnfa.alurachallenge.enums.Categoria;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequestDTO{

	@ApiModelProperty(notes = "Data da despesa", required = true, example = "30/12/2030")
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@ApiModelProperty(notes = "Descrição da despesa", required = true, example = "Aluguel")
	@NotBlank
    private String descricao;

	@ApiModelProperty(notes = "Categoria da despesa", required = true, example = "MORADIA")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
	
	@ApiModelProperty(notes = "Valor da despesa", required = true, example = "500.00")
    @DecimalMin("0.0")
    private double valor;
}