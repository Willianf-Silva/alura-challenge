package br.com.wnfa.alurachallenge.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomeRequestDTO{

	@ApiModelProperty(notes = "Data da receita", required = true, example = "05/12/2030")
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@ApiModelProperty(notes = "Descrição da receita", required = true, example = "Salário")
	@NotBlank
    private String descricao;

	@ApiModelProperty(notes = "Valor da receita", required = true, example = "900.00")
    @DecimalMin("0.0")
    private double valor;
}